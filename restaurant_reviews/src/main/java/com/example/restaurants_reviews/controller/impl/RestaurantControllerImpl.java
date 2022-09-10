package com.example.restaurants_reviews.controller.impl;

import com.example.restaurants_reviews.controller.RestaurantController;
import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.mapper.RestaurantMapper;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "restaurant", description = "The controller API")
@RestController
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantControllerImpl(RestaurantService restaurantService, ReviewService reviewService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
        this.restaurantMapper = restaurantMapper;
    }

    public Page<RestaurantOutDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> allRestaurants = restaurantService.getAllRestaurants(pageable);
        return allRestaurants.map(restaurantMapper::restaurantToRestaurantOutDTO);
    }

    public String getDescriptionByName(@PathVariable String name) {
        Restaurant restaurant = restaurantService.findRestaurantByName(name);
        return restaurant.getDescription();
    }

    public RestaurantInDTO addRestaurant(@RequestBody @Valid RestaurantInDTO restaurantInDTO) {
        restaurantService.addRestaurant(restaurantMapper.restaurantInDTOToRestaurantEntity(restaurantInDTO));
        return restaurantInDTO;
    }

    public void updateDescriptionByName(@PathVariable String name, @PathVariable String description) throws RestaurantNotFoundException {
        restaurantService.updateDescriptionByName(name, description);
    }

    public RestaurantOutDTO findRestaurantByName(@PathVariable String name) {
        Restaurant restaurant = restaurantService.findRestaurantByName(name);
        return restaurantMapper.restaurantToRestaurantOutDTO(restaurant);
    }

    public void addRestaurantByNameAndCreationDate(@PathVariable String name, @PathVariable LocalDate date)
            throws FoundationDateIsExpiredException {
        restaurantService.addRestaurantByNameAndCreationDate(name, date);
    }

    @Override
    public Page<String> getReviewsByName(String name, Pageable pageable) {
        List<String> reviews = reviewService.getReviewsByRestaurantName(name);
        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @Override
    public double getRatingByName(String name) {
        return reviewService.getRatingByRestaurantName(name);
    }

    @Override
    public Page<RestaurantSmallOutDTO> getSmallListRestaurants(Pageable pageable) {
        return restaurantService.getSmallList(pageable);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleArgumentFormatException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
