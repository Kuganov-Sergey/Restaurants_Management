package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findRestaurantByName(String name);

    @Query(value = "UPDATE Restaurant SET ownerId = NULL WHERE ownerId = :id")
    void deleteOwnerById(Long id);
}
