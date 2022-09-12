package com.example.restaurants_reviews.service;


import com.example.restaurants_reviews.dto.in.UpdateOwnerIdRestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateRestaurantInDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    Page<RestaurantEntity> getAllRestaurants(Pageable pageable);
    void addRestaurant(RestaurantEntity restaurantEntity);
    void updateOwner(UpdateOwnerIdRestaurantInDTO updateOwnerIdRestaurantInDTO) throws OwnerNotFoundException;
    Page<RestaurantSmallOutDTO> getSmallList(Pageable pageable);
    RestaurantOutDTO getRestaurantById(Long id) throws RestaurantNotFoundException;
    void updateRestaurantById(Long id, UpdateRestaurantInDTO restaurant) throws RestaurantNotFoundException;
    List<ReviewsByRestaurantIdOutDTO> getReviewsByRestaurantId(Long id);
}
