package com.santoshmane.zomato_app.repositories;

import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Long> {
    Optional<DeliveryPartner> findByUser(User user);

    @Query(value = "SELECT d.*, ST_Distance(d.current_location,:restaurantLocation) AS distance " +
            "FROM delivery_person d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location,:restaurantLocation,5000) " +
            "ORDER BY distance " +
            "LIMIT 10",nativeQuery = true )
    List<DeliveryPartner> findTenNearestDeliveryPartner(Point restaurantLocation);

    @Query(value = "SELECT d.* " +
            "FROM delivery_person d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location,:restaurantLocation,15000) " +
            "ORDER BY d.rating DESC " +
            "LIMIT 10",nativeQuery = true)
    List<DeliveryPartner> findTenNearbyTopRatedDeliveryPartner(Point restaurantLocation);
}
