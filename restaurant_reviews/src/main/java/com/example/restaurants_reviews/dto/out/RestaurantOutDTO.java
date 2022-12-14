package com.example.restaurants_reviews.dto.out;

import com.example.restaurants_reviews.entity.KitchenTypeE;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Schema(description = "phone number")
    private String phoneNumber;

    @Schema(description = "creation date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date;

    @Schema(description = "owner_id")
    private Long ownerId;

    @Schema(description = "update datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDateTime updateDatetime;

    @Schema(description = "kitchen type")
    @Enumerated(value = EnumType.STRING)
    private KitchenTypeE kitchenTypeE;

}
