package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.OrderRequest;
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
public class CustomerDto {

    private Long id;

    private UserDto user;

    private PointDto deliveryAddress;

    @JsonIgnore
    private List<OrderRequestDto> orders;

    private Double rating;
}
