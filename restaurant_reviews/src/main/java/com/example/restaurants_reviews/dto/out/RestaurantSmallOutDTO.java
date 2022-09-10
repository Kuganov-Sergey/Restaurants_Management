package com.example.restaurants_reviews.dto.out;

import lombok.*;

import java.math.BigDecimal;

@Data
public class RestaurantSmallOutDTO {

    private Long id;
    private String name;
    private BigDecimal avg;
}
