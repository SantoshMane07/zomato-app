package com.santoshmane.zomato_app.annotations;

import com.santoshmane.zomato_app.entities.enums.PaymentMethod;
import com.santoshmane.zomato_app.entities.enums.RestaurantType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTypeValidator implements ConstraintValidator<RestaurantTypeValidation,String> {

    @Override
    public boolean isValid(String restaurantType, ConstraintValidatorContext constraintValidatorContext) {
        if(restaurantType == null) return false;
        List<String> restaurantTypes = Arrays.stream(RestaurantType.values())
                .map(Enum::name)  // Convert each enum to its name
                .collect(Collectors.toList());
        return restaurantTypes.contains(restaurantType);
    }
}
