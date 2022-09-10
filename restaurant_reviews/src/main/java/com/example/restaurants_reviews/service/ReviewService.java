package com.example.restaurants_reviews.service;

import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;

import java.util.List;

public interface ReviewService {

    List<ReviewsByRestaurantIdOutDTO> getReviewsByRestaurantId(Long id);
    void addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException;
}
