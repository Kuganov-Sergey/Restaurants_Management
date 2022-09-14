package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.data.RestaurantSmall;
import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "updateDatetime", ignore = true)
    RestaurantOutDTO restaurantToRestaurantOutDTO(RestaurantEntity restaurantEntity);

    @Mapping(target = "updatedDatetime", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "id", ignore = true)
    RestaurantEntity restaurantInDTOToRestaurantEntity(RestaurantInDTO restaurantInDTO);

    RestaurantSmallOutDTO restaurantSmallToRestaurantSmallOutDTO(RestaurantSmall restaurantSmall);
}
