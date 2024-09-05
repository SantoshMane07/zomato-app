package com.santoshmane.zomato_app.strategies.impl;


import ch.qos.logback.core.util.DelayStrategy;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.repositories.DeliveryPartnerRepository;
import com.santoshmane.zomato_app.strategies.DeliveryPartnerMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryPartnerMatchingNearestDeliveryPartnerStrategy implements DeliveryPartnerMatchingStrategy {
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    @Override
    public List<DeliveryPartner> findMatchingDeliveryPartner(DeliveryRequest deliveryRequest) {
        return deliveryPartnerRepository.findTenNearestDeliveryPartner(deliveryRequest.getPickUpAddress());
    }
}
