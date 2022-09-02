package com.example.restaurants_reviews.dto.out;

import com.example.restaurants_reviews.entity.Restaurant;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewOutDTO {

    private Long id;
    private Restaurant restaurant_id;
    private String review;
    private Double rating;
}
