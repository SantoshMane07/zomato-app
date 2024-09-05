package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.MenuDto;
import com.santoshmane.zomato_app.dtos.MenuItemDto;
import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.Restaurant;

import java.util.List;

public interface MenuService {
    Menu createNewMenuForRestaurant(Menu menu, Restaurant restaurant);
    Menu updateMenuActiveStatus(Boolean status,Menu menu);
    Menu getMenuById(Long menuId);
    List<Menu> getMenusByRestaurant(Restaurant restaurant);
    Menu updateMenuOfRestaurant(Menu menu, Long menuId);
}
