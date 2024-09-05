package com.santoshmane.zomato_app.controllers;

import com.santoshmane.zomato_app.dtos.*;
import com.santoshmane.zomato_app.entities.Menu;
import com.santoshmane.zomato_app.services.RestaurantPartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurantPartner")
@Secured("ROLE_RESTAURANT_PARTNER")
public class RestaurantPartnerController {

    private final RestaurantPartnerService restaurantPartnerService;

    //Order
    @PostMapping("/acceptOrderRequest/{orderRequestId}")
    public CustomerOrderDto acceptOrderRequest(@PathVariable Long orderRequestId,@RequestBody AcceptOrderDto acceptOrderDto){
        return restaurantPartnerService.acceptOrderRequest(orderRequestId,acceptOrderDto.getEstimatedPreparationTime());
    }

    @PostMapping("/startDelivery/{confirmedDeliveryId}")
    public ResponseEntity<ConfirmedDeliveryDto> startDelivery(@PathVariable Long confirmedDeliveryId,@RequestBody StartDeliveryDto startDeliveryDto){
        return ResponseEntity.ok(restaurantPartnerService.startDelivery(confirmedDeliveryId,startDeliveryDto.getPickUpOtp()));
    }

    @PostMapping("/cancelOrder/{orderRequestId}")
    public ResponseEntity<OrderRequestDto> cancelOrderRequest(@PathVariable Long orderRequestId){
        return ResponseEntity.ok(restaurantPartnerService.cancelOrderRequest(orderRequestId));
    }

    //Restaurant
    @PostMapping("/createRestaurant")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody @Valid RestaurantDto restaurantDto){
        return  ResponseEntity.ok(restaurantPartnerService.createRestaurant(restaurantDto));
    }
    @PutMapping("/updateRestaurantDetails/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurantDetails(@RequestBody @Valid RestaurantDto restaurantDto, @PathVariable Long restaurantId){
        return ResponseEntity.ok(restaurantPartnerService.updateRestaurantDetails(restaurantDto,restaurantId));
    }
    @PutMapping("/updateRestaurantStatus/{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurantStatus(@RequestBody @Valid RestaurantStatusDto restaurantStatusDto,@PathVariable Long restaurantId){
        return ResponseEntity.ok(restaurantPartnerService.updateRestaurantStatus(restaurantStatusDto,restaurantId));
    }
    //Menu
    @PostMapping("/createMenuForRestaurant/{restaurantId}")
    public ResponseEntity<MenuDto> createMenuForRestaurant(@RequestBody @Valid MenuDto menuDto,@PathVariable Long restaurantId){
        return ResponseEntity.ok(restaurantPartnerService.createMenuForRestaurant(menuDto,restaurantId));
    }
    @PutMapping("/updateMenuOfRestaurant/{menuId}")
    public ResponseEntity<MenuDto> updateMenuOfRestaurant(@RequestBody @Valid MenuDto menuDto,@PathVariable Long menuId){
        return ResponseEntity.ok(restaurantPartnerService.updateMenuOfRestaurant(menuDto,menuId));
    }
    @PutMapping("/updateMenuStatus/{menuId}")
    public ResponseEntity<MenuDto> updateMenuStatus(@RequestBody @Valid MenuStatusDto menuStatusDto,@PathVariable Long menuId){
        return ResponseEntity.ok(restaurantPartnerService.updateMenuStatus(menuStatusDto,menuId));
    }
    //MenuItem
    @PostMapping("/createMenuItemForMenu/{menuId}")
    ResponseEntity<MenuItemDto> createMenuItemForMenu(@RequestBody @Valid MenuItemDto menuItemDto,@PathVariable Long menuId){
        return ResponseEntity.ok(restaurantPartnerService.createMenuItemForMenu(menuItemDto,menuId));
    }
    @PutMapping("/updateMenuItemOfMenu/{menuItemId}")
    ResponseEntity<MenuItemDto> updateMenuItemOfMenu(@RequestBody @Valid MenuItemDto menuItemDto,@PathVariable Long menuItemId){
        return ResponseEntity.ok(restaurantPartnerService.updateMenuItemOfMenu(menuItemDto,menuItemId));
    }
    @PutMapping("/updateMenuItemStatus/{menuItemId}")
    public ResponseEntity<MenuItemDto> updateMenuItemStatus(@RequestBody @Valid MenuItemStatusDto menuItemStatusDto,@PathVariable Long menuItemId){
        return ResponseEntity.ok(restaurantPartnerService.updateMenuItemStatus(menuItemStatusDto,menuItemId));
    }
    //Wallet
    @GetMapping("/walletTransactions")
    public ResponseEntity<List<WalletTransactionDto>> getWalletTransactions(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                                            @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet,pageSize, Sort.by(Sort.Direction.DESC,"timeStamp","id"));
        return ResponseEntity.ok(restaurantPartnerService.getWalletTransactions(pageRequest).getContent());
    }
}
