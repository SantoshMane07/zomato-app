package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.entities.enums.DeliveryRequestStatus;

public interface DeliveryRequestService {
    void createDeliveryRequest(CustomerOrder customerOrder);
    DeliveryRequest updateDeliveryRequestStatus(DeliveryRequest deliveryRequest,DeliveryRequestStatus deliveryRequestStatus);
    DeliveryRequest getDeliveryRequestById(Long deliveryRequestId);
}
