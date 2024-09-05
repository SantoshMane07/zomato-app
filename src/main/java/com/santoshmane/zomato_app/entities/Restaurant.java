package com.santoshmane.zomato_app.entities;

import com.santoshmane.zomato_app.entities.enums.RestaurantType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "restaurant_partner_id")
    private RestaurantPartner restaurantPartner;
    private String name;
    private String description;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point address;
    @Enumerated(EnumType.STRING)
    private RestaurantType restaurantType;
    private Double rating;
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;
    private Boolean isOpen;
}
