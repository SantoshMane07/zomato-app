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
        validatedBy = {PaymentTypeValidator.class}
)
public @interface PaymentTypeValidation {
    String message() default "Payment Type is not valid it has to be WALLET or CASH";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
