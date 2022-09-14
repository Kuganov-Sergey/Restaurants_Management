package com.example.restaurants_reviews.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
public class ReviewSmallOutDTO {

    @Schema(description = "review_id")
    private Long id;

    @Schema(description = "review text")
    private String review;

    @Schema(description = "review rating")
    private Integer rating;

    @Schema(description = "is full text")
    private Boolean full;
}
