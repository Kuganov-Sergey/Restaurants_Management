package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateRestaurantOutDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/restaurant")
public interface RestaurantController {

    @Operation(summary = "Get all restaurants")
    @GetMapping
    Page<RestaurantOutDTO> getAllRestaurants(Pageable pageable);

    @Operation(summary = "Create new restaurant")
    @PostMapping
    RestaurantInDTO addRestaurant(@RequestBody @Valid RestaurantInDTO restaurantInDTO);

    @Operation(summary = "Get all reviews by restaurant name")
    @GetMapping("/{id}/reviews")
    Page<ReviewsByRestaurantIdOutDTO> getReviewsById(@PathVariable Long id, Pageable pageable);

    @Operation(summary = "Get restaurants with fields id, name, average rating")
    @GetMapping("/smallList")
    Page<RestaurantSmallOutDTO> getSmallListRestaurants(Pageable pageable);

    @Operation(summary = "find restaurant by id")
    @GetMapping("/{id}")
    RestaurantOutDTO getRestaurantById(@PathVariable Long id) throws RestaurantNotFoundException;

    @Operation(summary = "update restaurant by id")
    @PutMapping("/{id}")
    void updateRestaurantById(@PathVariable Long id, @RequestBody @Valid UpdateRestaurantOutDTO restaurant)
            throws RestaurantNotFoundException;
}
