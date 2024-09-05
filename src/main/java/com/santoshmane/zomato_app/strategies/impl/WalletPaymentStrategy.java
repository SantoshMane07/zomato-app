package com.santoshmane.zomato_app.strategies.impl;

import com.santoshmane.zomato_app.entities.*;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;
import com.santoshmane.zomato_app.entities.enums.TransactionMethod;
import com.santoshmane.zomato_app.repositories.PaymentRepository;
import com.santoshmane.zomato_app.services.PaymentService;
import com.santoshmane.zomato_app.services.WalletService;
import com.santoshmane.zomato_app.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.FIXED_DELIVERY_CHARGE;
import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.MINIMUM_AMOUNT_FOR_FREE_DELIVERY;
import static com.santoshmane.zomato_app.utils.Constants.COMMISSION_ON_ORDER;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment, ConfirmedDelivery confirmedDelivery) {
        Customer customer = payment.getCustomerOrder().getCustomer();
        DeliveryRequest deliveryRequest = confirmedDelivery.getDeliveryRequest();
        CustomerOrder customerOrder = deliveryRequest.getCustomerOrder();

        RestaurantPartner restaurantPartner = payment.getCustomerOrder().getRestaurant().getRestaurantPartner();
        DeliveryPartner deliveryPartner =  confirmedDelivery.getDeliveryPartner();

        //Deduct money from customer's wallet
        Double grandTotal = customerOrder.getGrandTotal();
        walletService.deductMoneyFromWallet(customer.getUser(),grandTotal,null,customerOrder,TransactionMethod.ORDER);
        //Add money to delivery person's wallet
        Double amountToBeAdded = calculateDeliveryPartnerPay(customerOrder.getTotalAmount(),customerOrder.getDeliveryCharges());
        walletService.addMoneyToWallet(deliveryPartner.getUser(),amountToBeAdded,null,customerOrder, TransactionMethod.ORDER);
        //Add money to Restaurant Owner's wallet
        Double totalAmount=customerOrder.getTotalAmount()-(customerOrder.getTotalAmount()*COMMISSION_ON_ORDER);
        walletService.addMoneyToWallet(restaurantPartner.getUser(),totalAmount,null,customerOrder,TransactionMethod.ORDER);
        //Update payment status
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        payment.setPaymentTime(LocalDateTime.now());
        paymentRepository.save(payment);
    }
}
