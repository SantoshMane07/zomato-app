package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.entities.MenuItem;

public interface MenuItemService {
    MenuItem createMenuItemForMenu(MenuItem menuItem, Menu menu);
    MenuItem updateMenuItemForMenu(MenuItem menuItem, Menu menu);
    MenuItem updateMenuItemAvailabilityStatus(Boolean availableStatus,MenuItem menuItem);
    MenuItem getMenuItemById(Long menuItemId);
}
