package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.example.restaurants_reviews.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "restaurant_id", ignore = true)
    ReviewOutDTO reviewToReviewOutDTO(ReviewEntity reviewEntity);

    ReviewsByRestaurantIdOutDTO reviewToReviewsByRestaurantIdOutDTO(ReviewEntity reviewEntity);

    @Mapping(target = "restaurant_id", ignore = true)
    @Mapping(target = "id", ignore = true)
    ReviewEntity updateReviewOutDTOToReviewEntity(UpdateReviewInDTO updateReviewInDTO);

    ReviewOutDTO updateReviewOutDTOToReviewOutDTO(ReviewEntity reviewEntity);
}
