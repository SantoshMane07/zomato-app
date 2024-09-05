package com.santoshmane.zomato_app.entities;

import com.santoshmane.zomato_app.entities.enums.DeliveryRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private CustomerOrder customerOrder;
    private Double grandTotal;
    private Double distance;
    private Integer estimatedPreparationTime;
    @Enumerated(EnumType.STRING)
    private DeliveryRequestStatus deliveryRequestStatus;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point pickUpAddress;
    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point dropOffAddress;
    @CreationTimestamp
    private LocalDateTime deliveryRequestedTime;
}
