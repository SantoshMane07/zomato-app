package com.santoshmane.zomato_app.controllers;

import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.services.DeliveryPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deliveryPartner")
@Secured("ROLE_DELIVERY_PARTNER")
public class DeliveryPartnerController {
    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/acceptDeliveryRequest/{deliveryRequestId}")
    public ResponseEntity<ConfirmedDeliveryDto> acceptDeliveryRequest(@PathVariable Long deliveryRequestId){
        return ResponseEntity.ok(deliveryPartnerService.acceptDeliveryRequest(deliveryRequestId));
    }

    @PostMapping("/completeDelivery/{confirmedDeliveryId}")
    public ResponseEntity<ConfirmedDeliveryDto> completeConfirmedDeliveryOrder(@PathVariable Long confirmedDeliveryId,@RequestBody CompleteDeliveryDto completeDeliveryDto){
        return ResponseEntity.ok(deliveryPartnerService.completeConfirmedDeliveryOrder(confirmedDeliveryId, completeDeliveryDto.getCustomerOrderOtp()));
    }

    @PutMapping("/cancelConfirmedDelivery/{confirmedDeliveryId}")
    public ResponseEntity<ConfirmedDeliveryDto> cancelConfirmedDelivery(@PathVariable Long confirmedDeliveryId){
        return ResponseEntity.ok(deliveryPartnerService.cancelConfirmedDelivery(confirmedDeliveryId));
    }

    @PostMapping("/giveFeedbackToCustomer/{confirmedDeliveryId}")
    public ResponseEntity<CustomerDto> giveFeedbackToCustomer(@RequestBody CustomerFeedbackDto customerFeedbackDto, @PathVariable Long confirmedDeliveryId){
        return ResponseEntity.ok(deliveryPartnerService.giveFeedbackToCustomer(customerFeedbackDto,confirmedDeliveryId));
    }

    @GetMapping("/myWalletTransactions")
    public ResponseEntity<List<WalletTransactionDto>> getMyWalletTransactions(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                                              @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet,pageSize, Sort.by(Sort.Direction.DESC,"timeStamp","id"));
        return ResponseEntity.ok(deliveryPartnerService.getAllMyWalletTransactions(pageRequest).getContent());
    }

    @GetMapping("/myProfile")
    public ResponseEntity<DeliveryPartnerDto> getMyProfile(){
        return ResponseEntity.ok(deliveryPartnerService.getMyProfile());
    }

    @GetMapping("/confirmedDeliveries")
    public ResponseEntity<List<ConfirmedDeliveryDto>> getMyConfirmedDeliveries(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                                               @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet,pageSize,Sort.by(Sort.Direction.DESC,"deliveryAcceptedTime","id"));
        return ResponseEntity.ok(deliveryPartnerService.getAllMyConfirmedDeliveries(pageRequest).getContent());
    }
    @PutMapping("/updateMyLocation")
    public ResponseEntity<DeliveryPartnerDto> updateMyLocation(@RequestBody LocationDto locationDto){
        return ResponseEntity.ok(deliveryPartnerService.updateMyLocation(locationDto));
    }
}
