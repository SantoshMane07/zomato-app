package com.santoshmane.zomato_app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "onBoardedBy")
    private List<DeliveryPartner> deliveryPartners;
    @OneToMany(mappedBy = "addedBy")
    private List<RestaurantPartner> restaurantPartners;

}
