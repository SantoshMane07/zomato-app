package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.OrderRequestDto;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.OrderRequest;
import com.santoshmane.zomato_app.entities.enums.OrderRequestStatus;

import java.util.Optional;

public interface OrderRequestService {
    OrderRequestDto createOrderRequest(OrderRequest orderRequest);
    OrderRequest updateOrderRequestStatus(OrderRequest orderRequest,OrderRequestStatus orderRequestStatus);
    OrderRequest getOrderRequestById(Long orderRequestId);
    OrderRequest getOrderRequestByCart(Cart cart);
}
