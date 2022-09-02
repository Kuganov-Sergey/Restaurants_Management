package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.mapper.ReviewMapper;
import com.example.restaurants_reviews.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/{name}")
    public Page<String> getReviewsByName(@PathVariable String name, Pageable pageable) {
        List<String> reviews = reviewService.getReviewsByRestaurantName(name);
        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @GetMapping("/rating/{name}")
    public double getRatingByName(@PathVariable String name) {
        return reviewService.getRatingByRestaurantName(name);
    }

    @PostMapping("/new")
    public ReviewInDTO addReview(@RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException {
        reviewService.addReview(reviewInDTO.getRestaurant_id(), reviewInDTO.getReview(), reviewInDTO.getRating());
        return reviewInDTO;
    }
}
