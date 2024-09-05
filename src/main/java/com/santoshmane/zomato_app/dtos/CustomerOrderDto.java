package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.enums.CustomerOrderStatus;
import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {

    private Long id;
    private CustomerOrderStatus customerOrderStatus;
    //
    @JsonIgnore
    private CartDto cart;
    private Double totalAmount;
    private Double deliveryCharges;
    private Double platformFee;
    private Double grandTotal;
    @JsonIgnore
    private RestaurantDto restaurant;
    private CustomerDto customer;
    private PaymentMethod paymentMethod;
    private Integer estimatedPreparationTime;
    //
    private String otp;
    //
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderAcceptedTime;
}
