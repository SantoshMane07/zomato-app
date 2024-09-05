package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.CustomerOrderDto;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.OrderRequest;
import com.santoshmane.zomato_app.entities.enums.CustomerOrderStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.CustomerOrderRepository;
import com.santoshmane.zomato_app.services.CustomerOrderService;
import com.santoshmane.zomato_app.services.DeliveryRequestService;
import com.santoshmane.zomato_app.utils.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final ModelMapper modelMapper;
    @Override
    public CustomerOrder createCustomerOrder(OrderRequest orderRequest, Integer estimatedPreparationTime) {
        CustomerOrder customerOrder = CustomerOrder.builder()
                .customerOrderStatus(CustomerOrderStatus.PREPARING)
                .estimatedPreparationTime(estimatedPreparationTime)
                .orderAcceptedTime(LocalDateTime.now())
                .otp(OtpGenerator.generateRandomOtp())
                .totalAmount(orderRequest.getCart().getTotalAmount())
                .build();
        modelMapper.map(orderRequest,customerOrder);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder updateCustomerOrderStatus(CustomerOrder customerOrder, CustomerOrderStatus customerOrderStatus) {
        customerOrder.setCustomerOrderStatus(customerOrderStatus);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder getCustomerOrderById(Long customerOrderId) {
        return customerOrderRepository.findById(customerOrderId).orElseThrow(
                ()->new ResourceNotFoundException("Customer Order not found by id:"+customerOrderId)
        );
    }

    @Override
    public Page<CustomerOrder> getAllCustomerOrders(Customer customer, PageRequest pageRequest) {
        return customerOrderRepository.findByCustomer(customer,pageRequest);
    }
}
