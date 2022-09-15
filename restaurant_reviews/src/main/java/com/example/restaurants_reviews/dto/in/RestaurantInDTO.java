package com.example.restaurants_reviews.dto.in;

import com.example.restaurants_reviews.constraint.ValidCreationDate;
import com.example.restaurants_reviews.constraint.ValidPhoneNumber;
import com.example.restaurants_reviews.entity.KitchenTypeE;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantInDTO {

    @NotBlank(message = "Empty name")
    private String name;

    @NotBlank(message = "Empty description")
    private String description;

    @ValidPhoneNumber(message = "Invalid format phone number")
    private String phoneNumber;

    @Email(message = "Is not email format")
    private String emailAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @ValidCreationDate
    private LocalDate date;

    @NotNull(message = "Empty type")
    private KitchenTypeE kitchenTypeE;
}
