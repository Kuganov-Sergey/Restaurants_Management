package com.example.restaurants_reviews;

import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicesTest extends AppContextTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;
}
