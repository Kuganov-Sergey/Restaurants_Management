package com.example.restaurants_reviews.dto.in;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewInDTO {

    @NotBlank(message = "You cant create review with empty reference id field")
    private Long restaurant_id;

    @NotBlank(message = "Empty review!")
    private String review;

    @Min(value = 1, message = "Min rating by 1")
    @Max(value = 5, message = "Max rating by 5")
    private Integer rating;
}
