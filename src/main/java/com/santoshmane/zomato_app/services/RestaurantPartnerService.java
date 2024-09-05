package com.santoshmane.zomato_app.services;


import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.RestaurantPartner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RestaurantPartnerService {

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);
    RestaurantDto updateRestaurantDetails(RestaurantDto restaurantDto, Long restaurantId);

    CustomerOrderDto acceptOrderRequest(Long orderRequestId, Integer estimatedPreparationTime);
    OrderRequestDto cancelOrderRequest(Long orderRequestId);

    MenuDto createMenuForRestaurant(MenuDto menuDto, Long restaurantId);

    MenuItemDto updateMenuItemOfMenu(MenuItemDto menuItemDto,Long menuItemId);

    ConfirmedDeliveryDto startDelivery(Long confirmedDeliveryId, String pickUpOtp);

    RestaurantPartner getCurrentRestaurantPartner();

    MenuDto updateMenuOfRestaurant(MenuDto menuDto, Long menuId);

    MenuItemDto createMenuItemForMenu(MenuItemDto menuItemDto, Long menuId);

    RestaurantDto updateRestaurantStatus(RestaurantStatusDto restaurantStatusDto, Long restaurantId);

    MenuDto updateMenuStatus(MenuStatusDto menuStatusDto, Long menuId);

    MenuItemDto updateMenuItemStatus(MenuItemStatusDto menuItemStatusDto, Long menuItemId);

    Page<WalletTransactionDto> getWalletTransactions(PageRequest pageRequest);

    RestaurantPartner createNewRestaurantPartner(RestaurantPartner createRestaurantPartner);
}
