package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.CartDto;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.CartItem;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.enums.CartStatus;

import java.util.Optional;

public interface CartService {

    void deleteCart(Long cartId);
    CartDto removeCartItemFromCart(CartItem cartItem,Cart cart);
    Cart getCartById(Long cartId);
    Cart createNewCart(Restaurant restaurant,Customer customer);
    Optional<Cart> getCartByCustomerAndRestaurantAndCartStatus(Customer customer, Restaurant restaurant, CartStatus cartStatus);
    Cart updateCartStatus(Cart cart, CartStatus cartStatus);
}
