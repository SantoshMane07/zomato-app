package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.CustomerOrder;
import com.santoshmane.zomato_app.entities.DeliveryPartner;
import com.santoshmane.zomato_app.entities.DeliveryRequest;
import com.santoshmane.zomato_app.entities.enums.ConfirmedDeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmedDeliveryDto {
    private Long id;
    @JsonIgnore
    private DeliveryRequest deliveryRequest;
    private DeliveryPartnerDto deliveryPartner;
    private String pickUpOtp;
    private Double distance;
    private Double grandTotal;
    private Integer estimatedTime;
    private PointDto pickUpAddress;
    private PointDto dropOffAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryAcceptedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryCompleteTime;
    private ConfirmedDeliveryStatus confirmedDeliveryStatus;
}
