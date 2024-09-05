package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.dtos.MenuDto;
import com.santoshmane.zomato_app.dtos.MenuItemDto;
import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.Restaurant;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.MenuRepository;
import com.santoshmane.zomato_app.services.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    @Override
    public Menu createNewMenuForRestaurant(Menu menu, Restaurant restaurant) {
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenuActiveStatus(Boolean status,Menu menu) {
        menu.setIsActive(status);
        return menuRepository.save(menu);
    }

    @Override
    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(
                ()-> new ResourceNotFoundException("Menu not found by id:"+menuId)
        );
    }

    @Override
    public List<Menu> getMenusByRestaurant(Restaurant restaurant) {
        return menuRepository.findByRestaurant(restaurant);
    }

    @Override
    public Menu updateMenuOfRestaurant(Menu menu, Long menuId) {
        menu.setId(menuId);
        return menuRepository.save(menu);
    }
}
