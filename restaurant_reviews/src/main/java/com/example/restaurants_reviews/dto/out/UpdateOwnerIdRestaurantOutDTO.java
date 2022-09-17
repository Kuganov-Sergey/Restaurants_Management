package com.example.restaurants_reviews.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateOwnerIdRestaurantOutDTO {

    private Long oldUserId;
    private Long newUserId;
}