package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.RestaurantPartner;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Range;

public interface RestaurantService {
    Restaurant getRestaurantById(Long restaurantId);
    Restaurant createRestaurant(Restaurant restaurant,RestaurantPartner restaurantPartner);

    Restaurant getRestaurantByRestaurantPartner(RestaurantPartner restaurantPartner);
    Restaurant updateRestaurantDetails(Restaurant restaurant,Long restaurantId);

    Restaurant updateRestaurantStatus(Boolean isOpen, Restaurant restaurant);

    Page<Restaurant> getRestaurantsNearByCustomer(Point customerLocation, PageRequest pageRequest);
}
