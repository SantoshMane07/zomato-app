package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest,Long> {
    Optional<DeliveryRequest> findByCustomerOrder(CustomerOrder customerOrder);
}
