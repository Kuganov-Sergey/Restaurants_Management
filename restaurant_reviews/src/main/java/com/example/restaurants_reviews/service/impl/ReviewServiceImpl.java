package com.example.restaurants_reviews.service.impl;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.dao.ReviewRepository;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.entity.Review;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public List<String> getReviewsByRestaurantName(String name) {

        return reviewRepository.getReviewsByName(name);
    }

    @Override
    public double getRatingByRestaurantName(String name) {
        return reviewRepository.getRatingByName(name);
    }

    @Override
    public void addReview(Long restaurantId, String text, Integer rate) throws RestaurantNotFoundException {
        Optional<Restaurant> byId = restaurantRepository.findById(restaurantId);
        if (byId.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        Restaurant restaurant = byId.get();
        Review review = new Review(restaurant, text, rate);
        reviewRepository.save(review);
    }

    @Override
    public void updateReviewByRestaurantId(long id, String review) {
        Optional<Review> reviewNewObject = reviewRepository.findById(id);
        if (reviewNewObject.isPresent()) {
            reviewNewObject.get().setReview(review);
            reviewRepository.save(reviewNewObject.get());
        }
    }
}
