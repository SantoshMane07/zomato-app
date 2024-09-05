package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.CustomerOrderDto;
import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.OrderRequest;
import com.santoshmane.zomato_app.entities.enums.CustomerOrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;

public interface CustomerOrderService {
    CustomerOrder createCustomerOrder(OrderRequest orderRequest,Integer estimatedPreparationTime);
    CustomerOrder updateCustomerOrderStatus(CustomerOrder customerOrder, CustomerOrderStatus customerOrderStatus);
    CustomerOrder getCustomerOrderById(Long customerOrderId);

    Page<CustomerOrder> getAllCustomerOrders(Customer customer, PageRequest pageRequest);
}
