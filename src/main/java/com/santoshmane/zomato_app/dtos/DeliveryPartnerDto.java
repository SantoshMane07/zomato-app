package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.santoshmane.zomato_app.entities.Admin;
import com.santoshmane.zomato_app.entities.ConfirmedDelivery;
import com.santoshmane.zomato_app.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPartnerDto {

    private Long id;
    private Long aadharNo;
    private String vehicleId;
    private UserDto user;
    private PointDto currentLocation;
    @JsonProperty("isAvailable")
    private Boolean isAvailable;
    private Double rating;
    @JsonIgnore
    private List<ConfirmedDeliveryDto> confirmedDeliveries;
}
