package com.example.restaurants_reviews.controller.impl;

import com.example.restaurants_reviews.controller.ReviewController;
import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.exception.ReviewNotFoundException;
import com.example.restaurants_reviews.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "reviews", description = "The reviews API")
@RestController
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    public ReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public ReviewInDTO addReview(@RequestBody ReviewInDTO reviewInDTO) throws RestaurantNotFoundException {
        reviewService.addReview(reviewInDTO.getRestaurantId(), reviewInDTO.getReview(), reviewInDTO.getRating());
        return reviewInDTO;
    }

    @Override
    public ReviewOutDTO updateReview(UpdateReviewInDTO updateReviewInDTO, Long id) throws ReviewNotFoundException {
        return reviewService.updateReview(updateReviewInDTO, id);
    }

    @Override
    public ReviewOutDTO getReviewById(Long id) throws ReviewNotFoundException {
        return reviewService.getReviewById(id);
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
