package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateReviewOutDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.exception.ReviewNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
public interface ReviewController {

    @Operation(summary = "Create new review")
    @PostMapping
    ReviewInDTO addReview(@RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException;

    @Operation(summary = "Update review by id")
    @PutMapping("{id}")
    ReviewOutDTO updateReview(@RequestBody UpdateReviewOutDTO updateReviewOutDTO, @PathVariable Long id)
            throws ReviewNotFoundException;

    @Operation(summary = "Get reviews by id")
    @GetMapping("{id}")
    ReviewOutDTO getReviewById(@PathVariable Long id) throws ReviewNotFoundException;
}
