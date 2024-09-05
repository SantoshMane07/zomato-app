package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.Payment;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;

import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.FIXED_DELIVERY_CHARGE;
import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.MINIMUM_AMOUNT_FOR_FREE_DELIVERY;

public interface PaymentService {
    void processPayment(ConfirmedDelivery confirmedDelivery);
    void createNewPayment(CustomerOrder customerOrder);
}
