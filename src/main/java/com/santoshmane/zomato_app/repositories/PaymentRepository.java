package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.Payment;
import com.santoshmane.zomato_app.entities.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByCustomerOrder(CustomerOrder customerOrder);
}
