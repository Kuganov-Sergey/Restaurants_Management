package com.example.restaurants_reviews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Restaurant not found")
public class RestaurantNotFoundException extends Exception {

}
