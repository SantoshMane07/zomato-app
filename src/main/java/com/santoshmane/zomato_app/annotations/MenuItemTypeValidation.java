package com.santoshmane.zomato_app.annotations;


import com.santoshmane.zomato_app.entities.enums.MenuItemType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
        validatedBy = {MenuItemTypeValidator.class}
)
public @interface MenuItemTypeValidation {
    String message() default "Menu Item type is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
