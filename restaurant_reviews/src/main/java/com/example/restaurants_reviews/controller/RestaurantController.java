package com.example.restaurants_reviews.controller;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RequestMapping("/restaurant")
public interface RestaurantController {

    @Operation(summary = "Get all restaurants")
    @GetMapping
    Page<RestaurantOutDTO> getAllRestaurants(Pageable pageable);

    @Operation(summary = "Get description by restaurant name")
    @GetMapping("/{name}/description")
    String getDescriptionByName(@PathVariable String name);

    @Operation(summary = "Create new restaurant")
    @PostMapping
    RestaurantInDTO addRestaurant(@RequestBody @Valid RestaurantInDTO restaurantInDTO);

    //TODO подумать
    @PutMapping("/{name}/{description}")
    void updateDescriptionByName(@PathVariable String name, @PathVariable String description)
            throws RestaurantNotFoundException;

    @Operation(summary = "Find restaurant by name")
    @GetMapping("/{name}")
    RestaurantOutDTO findRestaurantByName(@PathVariable String name);

    @Operation(summary = "Get all reviews by restaurant name")
    @GetMapping("/{name}/reviews")
    Page<String> getReviewsByName(@PathVariable String name, Pageable pageable);

    @Operation(summary = "Get avg rating of restaurant by name")
    @GetMapping("/{name}/rating")
    double getRatingByName(@PathVariable String name);

    @Operation(summary = "Create new restaurant by name and creation date")
    @PutMapping("/{name}/{date}")
    void addRestaurantByNameAndCreationDate(@PathVariable String name, @PathVariable LocalDate date)
            throws FoundationDateIsExpiredException;
}
