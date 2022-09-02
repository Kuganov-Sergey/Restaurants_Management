package com.example.restaurants_reviews.service;


import com.example.restaurants_reviews.dto.in.AddOwnerInDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.IncorrectEmailAddressException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    String getDescriptionByName(String name) throws RestaurantNotFoundException;
    Page<Restaurant> getAllRestaurants(Pageable pageable);
    void addRestaurant(Restaurant restaurant);
    void updateDescriptionByName(String name, String description) throws RestaurantNotFoundException;
    Restaurant findRestaurantByName(String name);
    long addPhoneByRestaurantName(String name, String phone) throws RestaurantNotFoundException, NumberParseException;
    void addEmailAddressByName(String name, String emailAddress) throws FoundationDateIsExpiredException,
            IncorrectEmailAddressException, RestaurantNotFoundException;
    long addRestaurantByNameAndCreationDate(String name, LocalDate creationDate) throws FoundationDateIsExpiredException;
    LocalDate getCreationDateByRestaurantName(String name) throws RestaurantNotFoundException;
    Page<Restaurant> getPaginatedAllRestaurants(int pageNum, int pageSize);
    void addOwner(AddOwnerInDTO addOwnerInDTO);
}
