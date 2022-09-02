package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.entity.Review;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        RestaurantsReviewsApplication.class})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppContextTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewService reviewService;

    @BeforeAll
    void addRestaurantsAndReviewsInDB() throws RestaurantNotFoundException {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("mac");
        restaurant.setDescription("burgers");
        restaurantService.addRestaurant(restaurant);
        Review review = new Review();
        review.setReview("best place");
        review.setRestaurant_id(restaurantService.getAllRestaurants(Pageable.unpaged()).toList().get(0));
        review.setRating(5);
        reviewService.addReview(review.getRestaurant_id().getId(), review.getReview(), review.getRating());
    }

    @BeforeEach
    void setDefaultParameters() throws NumberParseException, RestaurantNotFoundException {
        restaurantService.addPhoneByRestaurantName("mac", "+79997771122");
    }

    @AfterAll
    void cleanTable() {
        restaurantRepository.deleteAll();
    }
}