package com.example.restaurants_reviews.constraint;

import com.example.restaurants_reviews.constraint.validations.CreationDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreationDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCreationDate {

    String message() default "Creation date before the current date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
