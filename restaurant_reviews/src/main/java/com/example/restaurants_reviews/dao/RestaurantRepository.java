package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findRestaurantByName(String name);
}
