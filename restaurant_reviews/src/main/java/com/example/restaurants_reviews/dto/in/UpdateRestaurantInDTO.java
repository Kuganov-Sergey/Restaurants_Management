package com.example.restaurants_reviews.dto.in;

import com.example.restaurants_reviews.constraint.ValidPhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRestaurantInDTO {

    @Schema(description = "name")
    @NotBlank(message = "Empty name")
    private String name;

    @Schema(description = "description text")
    @NotBlank(message = "Empty description")
    private String description;

    @Schema(description = "phone num")
    @ValidPhoneNumber(message = "Invalid format phone number")
    private String phoneNumber;

    @Schema(description = "email address")
    @Email(message = "Is not email format")
    private String emailAddress;

    @Schema(description = "owner id")
    private Long ownerId;
}
