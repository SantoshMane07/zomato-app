package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
