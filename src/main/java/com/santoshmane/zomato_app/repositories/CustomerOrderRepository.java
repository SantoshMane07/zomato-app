package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long> {
    Page<CustomerOrder> findByCustomer(Customer customer, PageRequest pageRequest);
}
