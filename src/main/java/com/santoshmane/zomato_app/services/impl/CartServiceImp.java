package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.CartDto;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.CartItem;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.enums.CartStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.CartRepository;
import com.santoshmane.zomato_app.services.CartItemService;
import com.santoshmane.zomato_app.services.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final CartItemService cartItemService;

    @Override
    public void deleteCart(Long cartId) {
        //Deleting the cart will also delete the child entities i.e cart item as we have added cascade type all in cart entity
        cartRepository.deleteById(cartId);
    }

    @Override
    public CartDto removeCartItemFromCart(CartItem cartItem, Cart cart) {
        cart.getCartItems().remove(cartItem);
        cart.setTotalAmount(cart.getTotalAmount()-cartItem.getQuantity()*cartItem.getMenuItem().getPrice());
        cartItemService.removeCartItemFromCart(cartItem);
        return modelMapper.map(cartRepository.save(cart),CartDto.class);
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(
                ()-> new ResourceNotFoundException("Cart not found by id:"+cartId)
        );
    }

    @Override
    public Cart createNewCart(Restaurant restaurant, Customer customer) {
        Cart cart = Cart.builder()
                .cartStatus(CartStatus.OPEN)
                .cartName(restaurant.getName())
                .restaurant(restaurant)
                .customer(customer)
                .totalAmount(0.0)
                .build();
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartByCustomerAndRestaurantAndCartStatus(Customer customer, Restaurant restaurant, CartStatus cartStatus) {
        return cartRepository.findByCustomerAndRestaurantAndCartStatus(customer,restaurant,CartStatus.OPEN);
    }

    @Override
    public Cart updateCartStatus(Cart cart, CartStatus cartStatus) {
        cart.setCartStatus(cartStatus);
        return cartRepository.save(cart);
    }
}
