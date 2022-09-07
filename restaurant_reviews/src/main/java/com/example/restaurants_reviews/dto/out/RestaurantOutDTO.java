package com.example.restaurants_reviews.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOutDTO {

    @Schema(description = "restaurant id")
    private Long id;

    @Schema(description = "name")
    private String name;

    @Schema(description = "description text")
    private String description;

    @Schema(description = "email address")
    private String emailAddress;

    @Schema(description = "phone num")
    private String phoneNumber;

    @Schema(description = "creation date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date;

}
