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

    @Test
    void findRestaurantByName() {
        String name = "mac";
        String newName = restaurantService.findRestaurantByName(name).getName();
        assertEquals(name, newName);
    }

    @Test
    void updateDescriptionByName() throws RestaurantNotFoundException {
        String newDescription = "best burgers";
        restaurantService.updateDescriptionByName("mac", newDescription);
        assertEquals(newDescription, restaurantService.getDescriptionByName("mac"));
        restaurantService.updateDescriptionByName("mac", "burgers");
    }


    @Test
    void addPhoneByRestaurantName() throws NumberParseException, RestaurantNotFoundException {
        String phone = "+79998887766";
        restaurantService.addPhoneByRestaurantName("mac", phone);
        assertEquals(phone, restaurantService.findRestaurantByName("mac").getPhoneNumber());
    }


    @Test
    void getReviewsByRestaurantName() {
        String review = "best place";
        System.out.println(reviewService.getReviewsByRestaurantName("mac"));
        assertEquals(review, reviewService.getReviewsByRestaurantName("mac").get(0));
    }

    @Test
    void getRatingByRestaurantName() {
        int expectedRating = 5;
        assertEquals(expectedRating, reviewService.getRatingByRestaurantName("mac"));
    }

//    @Test
//    void updateReviewByRestaurantId() {
//        String expectedReview = "best place";
//        reviewService.updateReviewByRestaurantId(restaurantService.getAllRestaurants().get(0).getId(), expectedReview);
//        assertEquals(expectedReview, reviewService.getReviewsByRestaurantName("mac").get(0));
//    }
}
