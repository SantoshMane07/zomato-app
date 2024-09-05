package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.santoshmane.zomato_app.annotations.MenuItemTypeValidation;
import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.enums.MenuItemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {

    private Long id;
    @NotBlank(message="Name of the menu item should be provided")
    private String name;
    @NotBlank(message="Description of the menu item should be provided")
    private String description;
    @JsonIgnore
    private MenuDto menu;
    @BooleanFlag
    @JsonProperty("isAvailable")
    private Boolean isAvailable;
    @MenuItemTypeValidation
    private String menuItemType;
    private Double rating;
    @NotNull(message = "Price should be provided")
    private Double price;
}
