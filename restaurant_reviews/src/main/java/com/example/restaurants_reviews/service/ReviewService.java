package com.example.restaurants_reviews.service;

import com.example.restaurants_reviews.exception.RestaurantNotFoundException;

import java.util.List;

public interface ReviewService {

    List<String> getReviewsByRestaurantName(String name);

    double getRatingByRestaurantName(String name);

    void addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException;

    void updateReviewByRestaurantId(long id, String review);
}
