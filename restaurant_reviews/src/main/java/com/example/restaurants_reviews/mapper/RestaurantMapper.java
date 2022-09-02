package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantOutDTO restaurantToRestaurantOutDTO(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    Restaurant restaurantInDTOToRestaurantEntity(RestaurantInDTO restaurantInDTO);

    @Mapping(target = "id", ignore = true)
    List<RestaurantOutDTO> restaurantListToRestaurantOutDTOList(List<Restaurant> restaurantList);
}
