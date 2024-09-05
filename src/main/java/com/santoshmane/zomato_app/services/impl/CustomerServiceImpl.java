package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.*;
import com.santoshmane.zomato_app.entities.enums.CartStatus;
import com.santoshmane.zomato_app.entities.enums.CustomerOrderStatus;
import com.santoshmane.zomato_app.entities.enums.OrderRequestStatus;
import com.santoshmane.zomato_app.exceptions.*;
import com.santoshmane.zomato_app.repositories.CustomerOrderRepository;
import com.santoshmane.zomato_app.repositories.CustomerRepository;
import com.santoshmane.zomato_app.repositories.MenuItemFeedbackRepository;
import com.santoshmane.zomato_app.repositories.UserRepository;
import com.santoshmane.zomato_app.services.*;
import com.santoshmane.zomato_app.utils.GeometryUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CartItemService cartItemService;
    private final MenuService menuService;
    private final MenuItemService menuItemService;
    private final CartService cartService;
    private final RestaurantService restaurantService;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final OrderRequestService orderRequestService;
    private final ModelMapper modelMapper;
    private final CustomerOrderService customerOrderService;
    private final FeedbackService feedbackService;
    private final MenuItemFeedbackService menuItemFeedbackService;
    private final MenuItemFeedbackRepository menuItemFeedbackRepository;
    private final WalletTransactionService walletTransactionService;
    private final WalletService walletService;
    private final CustomerOrderRepository customerOrderRepository;

    @Override
    @Transactional
    public CartItemDto addMenuItemToCart(Long menuItemId) {

        //Get Menu Item and check is it available or not
        MenuItem menuItem = menuItemService.getMenuItemById(menuItemId);
        if(!menuItem.getIsAvailable()){
             throw new ServiceUnavailableException("Sorry Menu Item is currently not available");
        }
        //Get Menu by MenuItem and check is it active or not
        Menu menu = menuService.getMenuById(menuItem.getMenu().getId());
        if(!menu.getIsActive()){
            throw new ServiceUnavailableException("Sorry Menu is currently not available");
        }
        //Get Restaurant from menu and check is it open for deliveries
        Restaurant restaurant = restaurantService.getRestaurantById(menu.getRestaurant().getId());
        if(!restaurant.getIsOpen()){
            throw new ServiceUnavailableException("Sorry Restaurant is currently not open for deliveries");
        }
        //Get current customer
        Customer customer = getCurrentCustomer();

        //Get Cart by customer and restaurant and check cart is present or not
        Optional<Cart> cartOptional = cartService.getCartByCustomerAndRestaurantAndCartStatus(customer,restaurant, CartStatus.OPEN);
        if(cartOptional.isPresent()){
            //Check if menuItem is already added to cart
            Cart cart = cartOptional.get();
            cart.getCartItems().stream()
                    .forEach(cartItem -> {
                        if(cartItem.getMenuItem().getId() == menuItemId){
                            throw new RuntimeConflictException("Menu Item is already added to cart");
                        }
                    });
            //Create new Cart Item and set existing cart to it
            return modelMapper.map(cartItemService.createNewCartItem(menuItem,cart),CartItemDto.class);
        }else{
            //Create new Cart and new CartItem and set cart to CartItem
            Cart newCart = cartService.createNewCart(restaurant,customer);
            return modelMapper.map(cartItemService.createNewCartItem(menuItem,newCart),CartItemDto.class);
        }
    }

    @Transactional
    @Override
    public CartItemDto incrementCartItemQuantity(Long cartItemId, Long cartId, Integer quantity) {
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+cartId+" is not associated with customer with id:"+customer.getId());
        }
        //Get CartItem and check if cartItem belong to cart
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        if(!cart.getCartItems().contains(cartItem)){
            throw new BadRequestException("CartItem with id:"+cartItemId+" does not belong to cart with id:"+cartId);
        }
        return cartItemService.incrementCartItemQuantity(quantity,cartItem);
    }

    @Transactional
    @Override
    public CartItemDto decrementCartItemQuantity(Long cartItemId, Long cartId, Integer quantity) {
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+cartId+" is not associated with customer with id:"+customer.getId());
        }
        //Get CartItem and check if cartItem belong to cart
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        if(!cart.getCartItems().contains(cartItem)){
            throw new BadRequestException("CartItem with id:"+cartItemId+" does not belong to cart with id:"+cartId);
        }
        return cartItemService.decrementCartItemQuantity(quantity,cartItem);
    }

    @Override
    public CartDto removeCartItemFromCart(Long cartItemId, Long cartId) {
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+cartId+" is not associated with customer with id:"+customer.getId());
        }
        //Get CartItem and check if cartItem belong to cart
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        if(!cart.getCartItems().contains(cartItem)){
            throw new BadRequestException("CartItem with id:"+cartItemId+" does not belong to cart with id:"+cartId);
        }

        return cartService.removeCartItemFromCart(cartItem,cart);
    }

    @Override
    public void deleteCart(Long cartId) {
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+cartId+" is not associated with customer with id:"+customer.getId());
        }
        cartService.deleteCart(cartId);
    }

    @Transactional
    @Override
    public OrderRequestDto placeOrder(OrderRequestDto orderRequestDto, Long cartId) {
        OrderRequest orderRequest = modelMapper.map(orderRequestDto, OrderRequest.class);
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+ cart.getId() +" is not associated with customer with id:"+customer.getId());
        }
        //Check if restaurant is open or closed
        Restaurant restaurant = restaurantService.getRestaurantById(cart.getRestaurant().getId());
        if(!restaurant.getIsOpen()){
            throw new ServiceUnavailableException("Sorry restaurant is currently closed");
        }
        //Allow to place order only if cart status is OPEN
        if(!cart.getCartStatus().equals(CartStatus.OPEN)){
            throw new BadRequestException("Failed to place order request cart status is:"+cart.getCartStatus().name());
        }
        //Create new order request
        orderRequest.setCustomer(customer);
        orderRequest.setRestaurant(restaurant);
        orderRequest.setCart(cart);
        cartService.updateCartStatus(cart,CartStatus.ORDERED);
        return orderRequestService.createOrderRequest(orderRequest);
    }

    @Transactional
    @Override
    public OrderRequestDto cancelOrderRequest(Long orderRequestId) {
        OrderRequest orderRequest = orderRequestService.getOrderRequestById(orderRequestId);
        Customer customer = getCurrentCustomer();
        //Check if this order request belongs to the current customer
        if(!orderRequest.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("This order request does not belong to the customer with id:"+customer.getId());
        }
        //Allow to cancel order only if order request status is Pending
        if(!orderRequest.getOrderRequestStatus().equals(OrderRequestStatus.PENDING)){
            throw new BadRequestException("Order request cannot be cancelled its status is:"+orderRequest.getOrderRequestStatus().name());
        }
        //Change order request status to cancelled
        return modelMapper.map(orderRequestService.updateOrderRequestStatus(orderRequest,OrderRequestStatus.CANCELLED), OrderRequestDto.class);
    }

    @Override
    public DeliveryPartnerDto giveFeedbackToDeliveryPartner(DeliveryPartnerFeedbackDto deliveryPartnerFeedbackDto, Long customerOrderId) {
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(customerOrderId);
        Customer customer = getCurrentCustomer();
        //Check customer Order belongs to customer
        if(!customerOrder.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cannot review and rate delivery person customer order with id:"+customerOrder.getId()+" does not belong to customer with id:"+customer.getId());
        }
        //Allow to review and rate delivery person only if customer order status is delivered
        if(!customerOrder.getCustomerOrderStatus().equals(CustomerOrderStatus.DELIVERED)){
            throw new BadRequestException("Cannot review and rate delivery person, customer order status is:"+customerOrder.getCustomerOrderStatus().name());
        }
        Feedback feedback = feedbackService.getFeedbackByCustomerOrder(customerOrder);
        //Check delivery is already reviewed and rated
        if(!(feedback.getDeliveryReview()==null && feedback.getDeliveryRating()==null)){
            throw new BadRequestException("Delivery Person is already reviewed and rated");
        }
        return feedbackService.giveFeedbackToDeliveryPartner(feedback,deliveryPartnerFeedbackDto.getRating(),deliveryPartnerFeedbackDto.getReview());
    }

    @Override
    public RestaurantDto giveFeedbackToRestaurant(RestaurantFeedbackDto restaurantFeedbackDto, Long customerOrderId) {
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(customerOrderId);
        Customer customer = getCurrentCustomer();
        //Check customer Order belongs to customer
        if(!customerOrder.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cannot review and rate restaurant, customer order with id:"+customerOrder.getId()+" does not belong to customer with id:"+customer.getId());
        }
        //Allow to review and rate restaurant only if customer order status is delivered
        if(!customerOrder.getCustomerOrderStatus().equals(CustomerOrderStatus.DELIVERED)){
            throw new BadRequestException("Cannot review and rate restaurant customer order status is:"+customerOrder.getCustomerOrderStatus().name());
        }
        Feedback feedback = feedbackService.getFeedbackByCustomerOrder(customerOrder);
        //Check Restaurant is already reviewed and rated
        if(!(feedback.getRestaurantReview()==null && feedback.getRestaurantRating()==null)){
            throw new BadRequestException("Restaurant is already reviewed and rated");
        }
        return feedbackService.giveFeedbackToRestaurant(feedback,restaurantFeedbackDto.getRating(),restaurantFeedbackDto.getReview());
    }

    @Override
    public MenuItemDto giveFeedbackToCartItem(CartItemFeedbackDto cartItemFeedbackDto, Long customerOrderId) {
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(customerOrderId);
        Customer customer = getCurrentCustomer();
        //Check customer Order belongs to customer
        if(!customerOrder.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cannot review and rate Cart item customer order with id:"+customerOrder.getId()+" does not belong to customer with id:"+customer.getId());
        }
        //Customer order status is completed then only allow to rate and review MenuItem
        if(!customerOrder.getCustomerOrderStatus().equals(CustomerOrderStatus.DELIVERED)){
            throw new BadRequestException("Cannot review and rate menuItem customer order status is:"+customerOrder.getCustomerOrderStatus().name());
        }
        Feedback feedback = feedbackService.getFeedbackByCustomerOrder(customerOrder);

        List<MenuItemFeedback> menuItemFeedbackList = menuItemFeedbackRepository.findByFeedback(feedback);
        CartItem cartItem = cartItemService.getCartItemById(cartItemFeedbackDto.getCartItemId());
        MenuItemFeedback menuItemFeedback = menuItemFeedbackList.stream()
                .filter(menuItemFeedbackObject -> menuItemFeedbackObject.getMenuItem().getId().equals(cartItem.getMenuItem().getId())
                ).findFirst().orElseThrow(()-> new ResourceNotFoundException("No menuItemFeedback found for Feedback with id:"+feedback.getId()+" and cart item with id:"+cartItem.getId()));
        //Check if MenuItem is already reviewed and rated
        if(!(menuItemFeedback.getMenuItemRating()==null && menuItemFeedback.getMenuItemReview()==null)){
            throw new BadRequestException("MenuItem is already reviewed and rated");
        }
        return menuItemFeedbackService.giveFeedbackToCartItem(menuItemFeedback, cartItemFeedbackDto.getRating(), cartItemFeedbackDto.getReview());
    }

    @Override
    public Page<CustomerOrderDto> getAllMyOrders(PageRequest pageRequest) {
        Customer customer = getCurrentCustomer();
        return customerOrderService.getAllCustomerOrders(customer,pageRequest).map(
                customerOrder -> modelMapper.map(customerOrder,CustomerOrderDto.class)
        );
    }

    @Override
    public Page<WalletTransactionDto> getAllMyWalletTransactions(PageRequest pageRequest) {
        Customer customer = getCurrentCustomer();
        Wallet wallet = walletService.findByUser(customer.getUser());
        return walletTransactionService.getAllWalletTransactionsByWallet(wallet,pageRequest).map(
                walletTransaction -> modelMapper.map(walletTransaction, WalletTransactionDto.class)
        );

    }

    @Override
    public CustomerDto getMyProfile() {
        Customer customer = getCurrentCustomer();
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CartDto getCartBtId(Long cartId) {
        //Get current customer,cart and check if cart belongs to the customer
        Customer customer = getCurrentCustomer();
        Cart cart = cartService.getCartById(cartId);
        if(!cart.getCustomer().getId().equals(customer.getId())){
            throw new AccessDeniedException("Cart with id:"+cartId+" is not associated with customer with id:"+customer.getId());
        }
        return modelMapper.map(cart,CartDto.class);
    }

    @Override
    public Page<RestaurantDto> getAllNearByRestaurants(PageRequest pageRequest) {
        Customer customer = getCurrentCustomer();
        return restaurantService.getRestaurantsNearByCustomer(customer.getDeliveryAddress(),pageRequest).map(
                restaurant -> modelMapper.map(restaurant,RestaurantDto.class)
        );
    }

    @Override
    public Customer createNewCustomer(User user) {
        Customer customer = Customer.builder()
                .user(user)
                .rating(0.0)
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDto updateMyLocation(LocationDto locationDto) {
        Customer customer = getCurrentCustomer();
        customer.setDeliveryAddress(GeometryUtil.createPoint(locationDto.getAddress()));
        return modelMapper.map(customerRepository.save(customer),CustomerDto.class);
    }

    @Override
    public Customer getCurrentCustomer() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerRepository.findByUser(user).orElseThrow(
                ()-> new ResourceNotFoundException("Customer not found by user id:"+1)
        );
    }
}
