package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.CartItemDto;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.CartItem;
import com.santoshmane.zomato_app.entities.MenuItem;

public interface CartItemService {
    CartItem getCartItemById(Long cartItemId);
    CartItem createNewCartItem(MenuItem menuItem, Cart cart);
    CartItemDto incrementCartItemQuantity(Integer quantity, CartItem cartItem);
    CartItemDto decrementCartItemQuantity(Integer quantity, CartItem cartItem);
    void removeCartItemFromCart(CartItem cartItem);
}
