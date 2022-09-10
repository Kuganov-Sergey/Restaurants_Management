package com.example.restaurants_reviews.controller.impl;

import com.example.restaurants_reviews.controller.ReviewController;
import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "reviews", description = "The reviews API")
@RestController
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    public ReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ReviewInDTO addReview(@RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException {
        reviewService.addReview(reviewInDTO.getRestaurant_id(), reviewInDTO.getReview(), reviewInDTO.getRating());
        return reviewInDTO;
    }
}
