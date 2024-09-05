package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.ConfirmedDeliveryDto;
import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.entities.enums.ConfirmedDeliveryStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.ConfirmedDeliveryRepository;
import com.santoshmane.zomato_app.services.ConfirmedDeliveryService;
import com.santoshmane.zomato_app.utils.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.santoshmane.zomato_app.utils.Constants.AVG_SPEED;

@Service
@RequiredArgsConstructor
public class ConfirmedDeliveryServiceImpl implements ConfirmedDeliveryService {
    private final ConfirmedDeliveryRepository confirmedDeliveryRepository;
    private final ModelMapper modelMapper;
    @Override
    public ConfirmedDeliveryDto createConfirmedDelivery(DeliveryRequest deliveryRequest, DeliveryPartner deliveryPartner) {

        ConfirmedDelivery confirmedDelivery = ConfirmedDelivery.builder()
                .confirmedDeliveryStatus(ConfirmedDeliveryStatus.ACCEPTED)
                .deliveryPartner(deliveryPartner)
                .deliveryRequest(deliveryRequest)
                .pickUpAddress(deliveryRequest.getPickUpAddress())
                .dropOffAddress(deliveryRequest.getDropOffAddress())
                .pickUpOtp(OtpGenerator.generateRandomOtp())
                .grandTotal(deliveryRequest.getGrandTotal())
                .distance(deliveryRequest.getDistance())
                .estimatedTime(deliveryRequest.getEstimatedPreparationTime()+estimatedDeliveryTime(deliveryRequest.getDistance())) //TODO - calculate estimated time
                .build();
        ConfirmedDelivery savedConfirmedDelivery=confirmedDeliveryRepository.save(confirmedDelivery);
        return modelMapper.map(savedConfirmedDelivery,ConfirmedDeliveryDto.class);
    }

    @Override
    public ConfirmedDelivery updateConfirmedDeliveryStatus(ConfirmedDelivery confirmedDelivery, ConfirmedDeliveryStatus confirmedDeliveryStatus) {
        confirmedDelivery.setConfirmedDeliveryStatus(confirmedDeliveryStatus);
        return confirmedDeliveryRepository.save(confirmedDelivery);
    }

    @Override
    public ConfirmedDelivery getConfirmedDeliveryById(Long confirmedDeliveryId) {
        return confirmedDeliveryRepository.findById(confirmedDeliveryId).orElseThrow(
                ()->new ResourceNotFoundException("Confirmed Delivery not found by id:"+confirmedDeliveryId)
        );
    }

    @Override
    public Page<ConfirmedDelivery> getConfirmedDeliveriesByDeliveryPartner(DeliveryPartner deliveryPartner, PageRequest pageRequest) {
        return confirmedDeliveryRepository.findByDeliveryPartner(deliveryPartner,pageRequest);
    }

    private Integer estimatedDeliveryTime(Double distance) {
        double averageSpeed = AVG_SPEED; // Average speed in km/h
        double baseTimeInMinutes = (distance / averageSpeed) * 60;
        int totalEstimatedTime = (int) Math.ceil(baseTimeInMinutes);
        return totalEstimatedTime;
    }
}
