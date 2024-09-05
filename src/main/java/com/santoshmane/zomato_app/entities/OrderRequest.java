package com.santoshmane.zomato_app.entities;


import com.santoshmane.zomato_app.entities.enums.OrderRequestStatus;
import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private Double deliveryCharges;
    private Double platformFee;
    private Double grandTotal;
    private OrderRequestStatus orderRequestStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "customer_id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}
