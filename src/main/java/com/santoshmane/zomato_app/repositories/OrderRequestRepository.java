package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRequestRepository extends JpaRepository<OrderRequest,Long> {
    Optional<OrderRequest> findByCart(Cart cart);
}
