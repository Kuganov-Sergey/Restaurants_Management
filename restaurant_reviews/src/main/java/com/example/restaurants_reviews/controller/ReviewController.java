package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.exception.ReviewNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/review")
public interface ReviewController {

    @Operation(summary = "Create new review")
    @PostMapping
    ReviewInDTO addReview(@Valid @RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException;

    @Operation(summary = "Update review by id")
    @PutMapping("{id}")
    ReviewOutDTO updateReview(@Valid @RequestBody UpdateReviewInDTO updateReviewInDTO, @PathVariable Long id)
            throws ReviewNotFoundException;

    @Operation(summary = "Get review by id")
    @GetMapping("{id}")
    ReviewOutDTO getReviewById(@PathVariable Long id) throws ReviewNotFoundException;
}
