package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.Payment;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.PaymentRepository;
import com.santoshmane.zomato_app.services.PaymentService;
import com.santoshmane.zomato_app.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.FIXED_DELIVERY_CHARGE;
import static com.santoshmane.zomato_app.strategies.DeliveryChargesCalculationStrategy.MINIMUM_AMOUNT_FOR_FREE_DELIVERY;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(ConfirmedDelivery confirmedDelivery) {
        Payment payment = paymentRepository.findByCustomerOrder(confirmedDelivery.getDeliveryRequest().getCustomerOrder()).orElseThrow(
                ()-> new ResourceNotFoundException("Payment not found for customer order with id:"+ confirmedDelivery.getId())
        );
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment,confirmedDelivery);
    }

    @Override
    public void createNewPayment(CustomerOrder customerOrder) {
        Payment payment = Payment.builder()
                .paymentMethod(customerOrder.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .customerOrder(customerOrder)
                .amount(customerOrder.getGrandTotal())
                .build();
        paymentRepository.save(payment);
    }
}
