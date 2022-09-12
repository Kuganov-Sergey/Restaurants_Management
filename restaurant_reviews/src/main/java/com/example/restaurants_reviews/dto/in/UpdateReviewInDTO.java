package com.example.restaurants_reviews.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReviewInDTO {

    @Schema(description = "review text")
    @NotBlank(message = "empty review")
    private String review;

    @Schema(description = "rating")
    @Min(value = 1, message = "Min rating by 1")
    @Max(value = 5, message = "Max rating by 5")
    private Integer rating;
}
