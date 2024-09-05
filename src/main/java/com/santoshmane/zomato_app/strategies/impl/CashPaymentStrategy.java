package com.santoshmane.zomato_app.strategies.impl;

import com.santoshmane.zomato_app.entities.*;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;
import com.santoshmane.zomato_app.entities.enums.TransactionMethod;
import com.santoshmane.zomato_app.repositories.ConfirmedDeliveryRepository;
import com.santoshmane.zomato_app.repositories.DeliveryRequestRepository;
import com.santoshmane.zomato_app.repositories.PaymentRepository;
import com.santoshmane.zomato_app.services.DeliveryRequestService;
import com.santoshmane.zomato_app.services.PaymentService;
import com.santoshmane.zomato_app.services.WalletService;
import com.santoshmane.zomato_app.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.*;
import static com.santoshmane.zomato_app.utils.Constants.COMMISSION_ON_ORDER;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment, ConfirmedDelivery confirmedDelivery) {

        DeliveryRequest deliveryRequest = confirmedDelivery.getDeliveryRequest();
        CustomerOrder customerOrder = deliveryRequest.getCustomerOrder();

        RestaurantPartner restaurantPartner = payment.getCustomerOrder().getRestaurant().getRestaurantPartner();
        DeliveryPartner deliveryPartner =  confirmedDelivery.getDeliveryPartner();
        //Deduct money from DeliveryPartner's wallet
        Double deliveryPartnerPay = calculateDeliveryPartnerPay(customerOrder.getTotalAmount(),customerOrder.getDeliveryCharges());
        Double amountToBeDeducted = confirmedDelivery.getGrandTotal()-deliveryPartnerPay;
        walletService.deductMoneyFromWallet(deliveryPartner.getUser(),amountToBeDeducted,null,customerOrder, TransactionMethod.ORDER);
        //Add money to Restaurant Owner's wallet
        Double amountToBeAdded=customerOrder.getTotalAmount()-(customerOrder.getTotalAmount()*COMMISSION_ON_ORDER);
        walletService.addMoneyToWallet(restaurantPartner.getUser(),amountToBeAdded,null,customerOrder,TransactionMethod.ORDER);
        //Update payment status
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

}
