package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.annotations.PaymentTypeValidation;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.enums.OrderRequestStatus;
import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long id;
    private CartDto cart;
    private Double deliveryCharges;
    private Double platformFee;
    private Double grandTotal;
    private OrderRequestStatus orderRequestStatus;
    @JsonIgnore
    private RestaurantDto restaurant;
    @JsonIgnore
    private CustomerDto customer;
    @NotNull
    @PaymentTypeValidation
    private String paymentMethod;
}
