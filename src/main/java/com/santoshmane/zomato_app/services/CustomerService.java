package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomerService {

    CartItemDto addMenuItemToCart(Long menuItemId);
    CartItemDto incrementCartItemQuantity(Long cartItemId, Long cartId, Integer quantity);

    CartItemDto decrementCartItemQuantity(Long cartItemId, Long cartId, Integer quantity);

    CartDto removeCartItemFromCart(Long cartItemId, Long cartId);
    void deleteCart(Long cartId);

    OrderRequestDto placeOrder(OrderRequestDto orderRequestDto,Long cartId);
    OrderRequestDto cancelOrderRequest(Long orderRequestId);

    DeliveryPartnerDto giveFeedbackToDeliveryPartner(DeliveryPartnerFeedbackDto deliveryPartnerFeedbackDto, Long customerOrderId);
    RestaurantDto giveFeedbackToRestaurant(RestaurantFeedbackDto restaurantFeedbackDto, Long customerOrderId);
    MenuItemDto giveFeedbackToCartItem(CartItemFeedbackDto cartItemFeedbackDto, Long customerOrderId);

    Page<CustomerOrderDto> getAllMyOrders(PageRequest pageRequest);

    Page<WalletTransactionDto> getAllMyWalletTransactions(PageRequest pageRequest);

    CustomerDto getMyProfile();

    Customer getCurrentCustomer();

    CartDto getCartBtId(Long cartId);

    Page<RestaurantDto> getAllNearByRestaurants(PageRequest pageRequest);

    Customer createNewCustomer(User user);

    CustomerDto updateMyLocation(LocationDto locationDto);
}
