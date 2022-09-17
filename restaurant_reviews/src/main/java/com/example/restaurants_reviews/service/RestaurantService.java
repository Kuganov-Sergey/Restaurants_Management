package com.example.restaurants_reviews.service;


import com.example.restaurants_reviews.dto.out.*;
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
    void updateOwnerInAllRestaurants(UpdateOwnerIdRestaurantOutDTO updateOwnerIdRestaurantOutDTO) throws RestaurantNotFoundException, OwnerNotFoundException;
//    void updateOwner(UpdateOwnerIdRestaurantInDTO updateOwnerIdRestaurantInDTO) throws OwnerNotFoundException, RestaurantNotFoundException;
    Page<RestaurantSmallOutDTO> getSmallList(Pageable pageable);
    RestaurantOutDTO getRestaurantById(Long id) throws RestaurantNotFoundException;
    void updateRestaurantById(Long id, UpdateRestaurantInDTO restaurant) throws RestaurantNotFoundException, OwnerNotFoundException;
    List<ReviewSmallOutDTO> getReviewsByRestaurantId(Long id);
    Long deleteRestaurantById(Long id) throws RestaurantNotFoundException;
    void deleteOwnerFromAllRestaurants(DeleteOwnerInRestaurantOutDTO deleteOwnerInRestaurantOutDTO);
}
