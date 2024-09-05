package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.Customer;
import com.santoshmane.zomato_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUser(User user);
}
