package com.santoshmane.zomato_app.dtos;

import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.enums.DeliveryRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDto {

    private Long id;

    private CustomerOrderDto customerOrder;

    private Double distance;

    private Double grandTotal;

    private Integer estimatedPreparationTime;

    private DeliveryRequestStatus deliveryRequestStatus;

    private PointDto pickUpAddress;

    private PointDto dropOffAddress;

    private LocalDateTime deliveryRequestedTime;
}
