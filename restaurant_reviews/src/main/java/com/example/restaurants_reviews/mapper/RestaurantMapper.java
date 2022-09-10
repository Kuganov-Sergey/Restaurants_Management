package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.controller.data.RestaurantSmall;
import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantOutDTO restaurantToRestaurantOutDTO(Restaurant restaurant);

    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "id", ignore = true)
    Restaurant restaurantInDTOToRestaurantEntity(RestaurantInDTO restaurantInDTO);

    @Mapping(target = "id", ignore = true)
    List<RestaurantOutDTO> restaurantListToRestaurantOutDTOList(List<Restaurant> restaurantList);

    RestaurantSmallOutDTO restaurantSmallToRestaurantSmallOutDTO(RestaurantSmall restaurantSmall);
}
