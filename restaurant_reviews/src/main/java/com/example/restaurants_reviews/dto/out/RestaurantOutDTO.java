package com.example.restaurants_reviews.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantOutDTO {

    private Long id;
    private String name;
    private String description;
    private String emailAddress;
    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date;

}
