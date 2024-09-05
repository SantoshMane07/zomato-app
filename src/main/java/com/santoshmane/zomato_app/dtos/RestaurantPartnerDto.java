package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santoshmane.zomato_app.entities.Admin;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPartnerDto {

    private Long id;

    private Long aadharNo;

    private UserDto user;
    @JsonIgnore
    private RestaurantDto restaurant;

}
