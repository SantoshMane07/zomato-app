package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.OrderRequestDto;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.OrderRequest;
import com.santoshmane.zomato_app.entities.enums.OrderRequestStatus;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.OrderRequestRepository;
import com.santoshmane.zomato_app.services.DistanceService;
import com.santoshmane.zomato_app.services.OrderRequestService;
import com.santoshmane.zomato_app.strategies.CustomerOrderStrategyManager;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.santoshmane.zomato_app.utils.Constants.PLATFORM_FEE;

@Service
@RequiredArgsConstructor
public class OrderRequestServiceImpl implements OrderRequestService {

    private final OrderRequestRepository orderRequestRepository;
    private final CustomerOrderStrategyManager customerOrderStrategyManager;
    private final DistanceService distanceService;
    private final ModelMapper modelMapper;

    @Override
    public OrderRequestDto createOrderRequest(OrderRequest orderRequest) {

        double totalAmount = orderRequest.getCart().getTotalAmount();
        Point source = orderRequest.getCustomer().getDeliveryAddress();
        Point destination = orderRequest.getRestaurant().getAddress();
        double distance = distanceService.calculateDistance(source, destination);
        double deliveryCharges = customerOrderStrategyManager.deliveryChargesCalculationStrategy().calculateDeliveryCharges(totalAmount,distance);
        //Create new order request with status pending and make status accepted after Restaurant owner accepts the order
        orderRequest.setOrderRequestStatus(OrderRequestStatus.PENDING);
        //Calculate the delivery charges and set, also set the Platform fees
        orderRequest.setDeliveryCharges(deliveryCharges);
        orderRequest.setPlatformFee(PLATFORM_FEE);
        //Set Grand total
        orderRequest.setGrandTotal(totalAmount+deliveryCharges+PLATFORM_FEE);
        //Save Order request
        OrderRequest savedOrderRequest = orderRequestRepository.save(orderRequest);
        //TODO:-Notify Restaurant owner about order
        return modelMapper.map(savedOrderRequest,OrderRequestDto.class);
    }

    @Override
    public OrderRequest updateOrderRequestStatus(OrderRequest orderRequest, OrderRequestStatus orderRequestStatus) {
        orderRequest.setOrderRequestStatus(orderRequestStatus);
        return orderRequestRepository.save(orderRequest);
    }

    @Override
    public OrderRequest getOrderRequestById(Long orderRequestId) {
        return orderRequestRepository.findById(orderRequestId).orElseThrow(
                ()-> new ResourceNotFoundException("Order request not found by id:"+orderRequestId)
        );
    }

    @Override
    public OrderRequest getOrderRequestByCart(Cart cart) {
        return orderRequestRepository.findByCart(cart).orElseThrow(
                ()->new ResourceNotFoundException("Order request not found for cart with id:"+cart.getId())
        );
    }
}
