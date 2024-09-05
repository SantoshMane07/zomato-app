package com.santoshmane.zomato_app.strategies;

import com.santoshmane.zomato_app.entities.OrderRequest;

public interface DeliveryChargesCalculationStrategy {
    double FIXED_DELIVERY_CHARGE = 25.0;
    double FIXED_PER_KILOMETER_CHARGE = 10;
    double FIXED_FREE_DELIVERY_DISTANCE = 5.0;
    double MINIMUM_AMOUNT_FOR_FREE_DELIVERY = 300;

    double calculateDeliveryCharges(double totalAmount,double distance);
}
