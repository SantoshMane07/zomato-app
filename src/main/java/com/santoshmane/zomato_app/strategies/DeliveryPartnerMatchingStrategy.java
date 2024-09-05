package com.santoshmane.zomato_app.strategies;

import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;

import java.util.List;

public interface DeliveryPartnerMatchingStrategy {
    List<DeliveryPartner> findMatchingDeliveryPartner(DeliveryRequest deliveryRequest);
}
