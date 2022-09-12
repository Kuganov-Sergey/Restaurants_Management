package com.example.restaurants_reviews.service.impl;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.dao.ReviewRepository;
import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateReviewOutDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import com.example.restaurants_reviews.entity.ReviewEntity;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.exception.ReviewNotFoundException;
import com.example.restaurants_reviews.mapper.ReviewMapper;
import com.example.restaurants_reviews.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final RestaurantRepository restaurantRepository;

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional
    public List<ReviewsByRestaurantIdOutDTO> getReviewsByRestaurantId(Long id) {
        List<ReviewEntity> reviewsById = reviewRepository.getReviewsById(id);
        return reviewsById.stream().map(reviewMapper::reviewToReviewsByRestaurantIdOutDTO).toList();
    }

    @Override
    public void addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> byId = restaurantRepository.findById(restaurantId);
        if (byId.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        RestaurantEntity restaurantEntity = byId.get();
        ReviewEntity reviewEntity = new ReviewEntity(restaurantEntity, text, rate);
        reviewRepository.save(reviewEntity);
    }

    @Override
    @Transactional
    public ReviewOutDTO updateReview(UpdateReviewOutDTO updateReviewOutDTO, Long id) throws ReviewNotFoundException {
        Optional<ReviewEntity> reviewEntityOptional = reviewRepository.findById(id);
        if (reviewEntityOptional.isEmpty()) {
            throw new ReviewNotFoundException();
        }
        reviewEntityOptional.get().setReview(updateReviewOutDTO.getReview());
        reviewEntityOptional.get().setRating(updateReviewOutDTO.getRating());
        return reviewMapper.updateReviewOutDTOToReviewOutDTO(reviewEntityOptional.get());
    }

    @Override
    public ReviewOutDTO getReviewById(Long id) throws ReviewNotFoundException {
        Optional<ReviewEntity> reviewEntityOptional = reviewRepository.findById(id);
        if (reviewEntityOptional.isEmpty()) {
            throw new ReviewNotFoundException();
        }
        return reviewMapper.reviewToReviewOutDTO(reviewEntityOptional.get());
    }
}
