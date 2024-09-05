package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    // Method to find a Cart by Customer, Restaurant, and CartStatus
    Optional<Cart> findByCustomerAndRestaurantAndCartStatus(Customer customer, Restaurant restaurant, CartStatus cartStatus);
}
