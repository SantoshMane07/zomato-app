package com.santoshmane.zomato_app.strategies.impl;

import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.repositories.DeliveryPartnerRepository;
import com.santoshmane.zomato_app.strategies.DeliveryPartnerMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryPartnerMatchingHighestRatedDeliveryPartnerStrategy implements DeliveryPartnerMatchingStrategy {
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    @Override
    public List<DeliveryPartner> findMatchingDeliveryPartner(DeliveryRequest deliveryRequest) {
        return deliveryPartnerRepository.findTenNearbyTopRatedDeliveryPartner(deliveryRequest.getPickUpAddress());
    }
}
