package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.santoshmane.zomato_app.entities.CartItem;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.enums.CartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;
    private String cartName;
    private List<CartItemDto> cartItems;
    private Double totalAmount;
    private CartStatus cartStatus;
    @JsonIgnore
    private CustomerDto customer;
    @JsonIgnore
    private RestaurantDto restaurant;
}
