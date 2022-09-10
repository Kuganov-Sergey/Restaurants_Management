package com.example.restaurants_reviews.controller.impl;

import com.example.restaurants_reviews.controller.RestaurantController;
import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateRestaurantOutDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
        Page<RestaurantEntity> allRestaurants = restaurantService.getAllRestaurants(pageable);
        return allRestaurants.map(restaurantMapper::restaurantToRestaurantOutDTO);
    }

    public RestaurantInDTO addRestaurant(@RequestBody @Valid RestaurantInDTO restaurantInDTO) {
        restaurantService.addRestaurant(restaurantMapper.restaurantInDTOToRestaurantEntity(restaurantInDTO));
        return restaurantInDTO;
    }

    @Override
    public Page<ReviewsByRestaurantIdOutDTO> getReviewsById(Long id, Pageable pageable) {
        List<ReviewsByRestaurantIdOutDTO> reviews = reviewService.getReviewsByRestaurantId(id);
        return new PageImpl<>(reviews, pageable, reviews.size());
    }

    @Override
    public Page<RestaurantSmallOutDTO> getSmallListRestaurants(Pageable pageable) {
        return restaurantService.getSmallList(pageable);
    }

    @Override
    public RestaurantOutDTO getRestaurantById(Long id) throws RestaurantNotFoundException {
        return restaurantService.getRestaurantById(id);
    }

    @Override
    public void updateRestaurantById(Long id, UpdateRestaurantOutDTO restaurant) throws RestaurantNotFoundException {
        restaurantService.updateRestaurantById(id, restaurant);
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
