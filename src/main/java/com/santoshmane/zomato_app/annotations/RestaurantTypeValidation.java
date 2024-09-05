package com.santoshmane.zomato_app.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Constraint(
        validatedBy = {RestaurantTypeValidator.class}
)
public @interface RestaurantTypeValidation {
    String message() default "Restaurant Type is not valid it has to be VEG, VEG_NON_VEG, NON_VEG";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
