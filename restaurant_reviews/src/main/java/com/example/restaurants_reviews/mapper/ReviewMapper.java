package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.data.ReviewSmall;
import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewSmallOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewsByRestaurantIdOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.example.restaurants_reviews.entity.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "restaurantId", source = "restaurantId.id")
    ReviewOutDTO reviewToReviewOutDTO(ReviewEntity reviewEntity);

    ReviewsByRestaurantIdOutDTO reviewToReviewsByRestaurantIdOutDTO(ReviewEntity reviewEntity);

    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "id", ignore = true)
    ReviewEntity updateReviewOutDTOToReviewEntity(UpdateReviewInDTO updateReviewInDTO);

    @Mapping(target = "restaurantId", source = "restaurantId.id")
    ReviewOutDTO updateReviewOutDTOToReviewOutDTO(ReviewEntity reviewEntity);

    ReviewSmallOutDTO reviewSmallToReviewSmallOutDTO(ReviewSmall reviewSmall);
}
