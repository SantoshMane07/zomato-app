package com.santoshmane.zomato_app.strategies.impl;

import com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class DeliveryChargesDefaultCalculationStrategy implements DeliveryChargesCalculationStrategy {
    @Override
    public double calculateDeliveryCharges(double totalAmount, double distance) {

        double deliveryCharges = 0.0;
        if (distance > FIXED_FREE_DELIVERY_DISTANCE) {
            deliveryCharges = FIXED_PER_KILOMETER_CHARGE * (distance - FIXED_FREE_DELIVERY_DISTANCE);
            if (totalAmount < MINIMUM_AMOUNT_FOR_FREE_DELIVERY) {
                deliveryCharges += FIXED_DELIVERY_CHARGE;
            }
        } else if (totalAmount <= MINIMUM_AMOUNT_FOR_FREE_DELIVERY) {
            deliveryCharges = FIXED_DELIVERY_CHARGE;
        }
        return deliveryCharges;
    }
}
