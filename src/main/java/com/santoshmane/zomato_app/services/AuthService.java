package com.santoshmane.zomato_app.services;

import com.santoshmane.zomato_app.dtos.*;

public interface AuthService {

    String[] login(String email,String password);

    UserDto signup(SignupDto signupDto);

    DeliveryPartnerDto onBoardDeliveryPartner(Long userId, OnBoardDeliveryPartnerDto onBoardDeliveryPartnerDto);

    RestaurantPartnerDto onBoardRestaurantPartner(Long userId,OnBoardRestaurantPartnerDto onBoardRestaurantPartnerDto);

    String refreshToken(String refreshToken);
}
