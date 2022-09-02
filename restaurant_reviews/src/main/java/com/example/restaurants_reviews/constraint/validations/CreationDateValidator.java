package com.example.restaurants_reviews.constraint.validations;

import com.example.restaurants_reviews.constraint.ValidCreationDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CreationDateValidator implements ConstraintValidator<ValidCreationDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return (value == null || LocalDate.now().isAfter(value));
    }

    @Override
    public void initialize(ValidCreationDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
