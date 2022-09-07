package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/review")
public interface ReviewController {

    @Operation(summary = "Create new review")
    @PostMapping
    ReviewInDTO addReview(@RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException;
}
