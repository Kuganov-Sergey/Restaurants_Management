package com.example.restaurants_reviews.constraint.validations;

import com.example.restaurants_reviews.constraint.ValidRestaurantExists;
import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.entity.RestaurantEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class RestaurantExistsValidator implements ConstraintValidator<ValidRestaurantExists, Long> {

    private final RestaurantRepository restaurantRepository;

    public RestaurantExistsValidator(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<RestaurantEntity> restaurant = restaurantRepository.findById(value);
        return restaurant.isPresent();
    }

    @Override
    public void initialize(ValidRestaurantExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
