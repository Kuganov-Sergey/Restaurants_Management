package com.example.restaurants_reviews.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsByRestaurantIdOutDTO {

    @Schema(description = "review id")
    private Long id;

    @Schema(description = "review text")
    private String review;

    @Schema(description = "rating")
    private Double rating;
}
