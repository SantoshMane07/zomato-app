package com.santoshmane.zomato_app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.santoshmane.zomato_app.entities.Cart;
import com.santoshmane.zomato_app.entities.MenuItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private MenuItemDto menuItem;
    private Integer quantity;
    @JsonIgnore
    private CartDto cart;
}
