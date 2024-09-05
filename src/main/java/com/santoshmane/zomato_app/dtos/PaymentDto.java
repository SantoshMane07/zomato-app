package com.santoshmane.zomato_app.dtos;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long id;

    private PaymentMethod paymentMethod;

    private CustomerOrderDto customerOrder;

    private PaymentStatus paymentStatus;

    private double amount;

    private LocalDateTime paymentTime;
}
