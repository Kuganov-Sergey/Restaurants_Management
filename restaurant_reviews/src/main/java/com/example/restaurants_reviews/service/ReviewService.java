package com.example.restaurants_reviews.service;

import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.exception.ReviewNotFoundException;

public interface ReviewService {

    void addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException;
    ReviewOutDTO updateReview(UpdateReviewInDTO updateReviewInDTO, Long id) throws ReviewNotFoundException;
    ReviewOutDTO getReviewById(Long id) throws ReviewNotFoundException;
}
