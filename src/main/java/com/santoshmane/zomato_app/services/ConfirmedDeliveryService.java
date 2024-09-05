package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.ConfirmedDeliveryDto;
import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.entities.enums.ConfirmedDeliveryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ConfirmedDeliveryService {
    ConfirmedDeliveryDto createConfirmedDelivery(DeliveryRequest deliveryRequest, DeliveryPartner deliveryPartner);
    ConfirmedDelivery updateConfirmedDeliveryStatus(ConfirmedDelivery confirmedDelivery,ConfirmedDeliveryStatus confirmedDeliveryStatus);
    ConfirmedDelivery getConfirmedDeliveryById(Long confirmedDeliveryId);

    Page<ConfirmedDelivery> getConfirmedDeliveriesByDeliveryPartner(DeliveryPartner deliveryPartner, PageRequest pageRequest);
}
