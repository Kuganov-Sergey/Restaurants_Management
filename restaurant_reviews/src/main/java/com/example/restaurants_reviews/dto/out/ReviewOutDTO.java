package com.example.restaurants_reviews.dto.out;

import com.example.restaurants_reviews.entity.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewOutDTO {

    @Schema(description = "review id")
    private Long id;

    @Schema(description = "restaurant id")
    private Restaurant restaurant_id;

    @Schema(description = "review text")
    private String review;

    @Schema(description = "rating")
    private Double rating;
}
