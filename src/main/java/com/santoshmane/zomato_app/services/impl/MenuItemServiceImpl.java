package com.santoshmane.zomato_app.services.impl;

import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.MenuItem;
import com.santoshmane.zomato_app.exceptions.ResourceNotFoundException;
import com.santoshmane.zomato_app.repositories.MenuItemRepository;
import com.santoshmane.zomato_app.services.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Override
    public MenuItem createMenuItemForMenu(MenuItem menuItem, Menu menu) {
        menuItem.setMenu(menu);
        menuItem.setRating(0.0);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItemForMenu(MenuItem menuItem, Menu menu) {
        menuItem.setMenu(menu);
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItemAvailabilityStatus(Boolean availableStatus, MenuItem menuItem) {
        menuItem.setIsAvailable(availableStatus);
        return menuItemRepository.save(menuItem);
    }



    @Override
    public MenuItem getMenuItemById(Long menuItemId) {
        return menuItemRepository.findById(menuItemId).orElseThrow(
                ()-> new ResourceNotFoundException("MenuItem not found by id:"+menuItemId)
        );
    }
}
