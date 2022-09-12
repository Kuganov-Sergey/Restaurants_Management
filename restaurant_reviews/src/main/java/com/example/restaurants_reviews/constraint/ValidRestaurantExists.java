package com.example.restaurants_reviews.constraint;

import com.example.restaurants_reviews.constraint.validations.RestaurantExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RestaurantExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRestaurantExists {
    String message() default "Restaurant not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
