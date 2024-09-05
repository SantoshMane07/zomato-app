package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.RestaurantPartner;
import com.santoshmane.zomato_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantPartnerRepository extends JpaRepository<RestaurantPartner,Long> {
    Optional<RestaurantPartner> findByUser(User user);
}
