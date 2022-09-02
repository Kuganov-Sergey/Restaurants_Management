package com.example.restaurants_reviews.mapper;

import com.example.restaurants_reviews.dto.out.ReviewOutDTO;
import com.example.restaurants_reviews.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewOutDTO reviewToReviewOutDTO(Review review);

}
