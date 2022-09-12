package com.example.restaurants_reviews.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Data
public class RestaurantSmallOutDTO {

    @Schema(description = "restaurant id")
    private Long id;

    @Schema(description = "restaurant name")
    private String name;

    @Schema(description = "Average rating by restaurant")
    private BigDecimal avg;
}
