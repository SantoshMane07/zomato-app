package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.entities.enums.DeliveryRequestStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.DeliveryRequestRepository;
import com.santoshmane.zomato_app.services.DeliveryRequestService;
import com.santoshmane.zomato_app.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRequestServiceImpl implements DeliveryRequestService {
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final DistanceService distanceService;
    @Override
    public void createDeliveryRequest(CustomerOrder customerOrder) {
        //
        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .deliveryRequestStatus(DeliveryRequestStatus.PENDING)
                .customerOrder(customerOrder)
                .distance(distanceService.calculateDistance(customerOrder.getCustomer().getDeliveryAddress(),customerOrder.getRestaurant().getAddress()))
                .grandTotal(customerOrder.getGrandTotal())
                .pickUpAddress(customerOrder.getRestaurant().getAddress())
                .dropOffAddress(customerOrder.getCustomer().getDeliveryAddress())
                .estimatedPreparationTime(customerOrder.getEstimatedPreparationTime())
                .build();
        //
        deliveryRequestRepository.save(deliveryRequest);
        //
        //TODO-Notify nearby DeliverPersons about the delivery request
    }

    @Override
    public DeliveryRequest updateDeliveryRequestStatus(DeliveryRequest deliveryRequest, DeliveryRequestStatus deliveryRequestStatus) {
        deliveryRequest.setDeliveryRequestStatus(deliveryRequestStatus);
        return deliveryRequestRepository.save(deliveryRequest);
    }

    @Override
    public DeliveryRequest getDeliveryRequestById(Long deliveryRequestId) {
        return deliveryRequestRepository.findById(deliveryRequestId).orElseThrow(
                ()->new ResourceNotFoundException("Delivery request not found by id:"+deliveryRequestId)
        );
    }
}
