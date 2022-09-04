package com.example.restaurants_reviews.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeleteOwnerInRestaurantOutDTO {

    private Long ownerId;
}
