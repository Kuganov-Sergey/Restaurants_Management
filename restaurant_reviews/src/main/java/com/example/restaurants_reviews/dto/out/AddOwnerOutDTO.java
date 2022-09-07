package com.example.restaurants_reviews.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddOwnerOutDTO {

    @Schema(description = "owner id")
    private Long ownerId;
}
