package com.santoshmane.zomato_app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long aadharNo;
    private String vehicleId;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point currentLocation;
    private Boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin onBoardedBy;
    private Double rating;
    @OneToMany(mappedBy = "deliveryPartner")
    private List<ConfirmedDelivery> confirmedDeliveries;
}
