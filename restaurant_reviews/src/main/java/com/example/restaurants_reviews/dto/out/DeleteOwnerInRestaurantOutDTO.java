package com.example.restaurants_reviews.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Tag(name = "user", description = "dto for docker,  it sends a message to remove the owner from the restaurant")
public class DeleteOwnerInRestaurantOutDTO {

    @Schema(description = "user id")
    private Long ownerId;
}
