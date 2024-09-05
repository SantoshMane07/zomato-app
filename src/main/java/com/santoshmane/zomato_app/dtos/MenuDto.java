package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.entities.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Long id;
    @NotBlank(message="Name of the menu should be provided")
    private String menuName;
    private List<MenuItemDto> menuItems;
    @BooleanFlag
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonIgnore
    private RestaurantDto restaurant;
}
