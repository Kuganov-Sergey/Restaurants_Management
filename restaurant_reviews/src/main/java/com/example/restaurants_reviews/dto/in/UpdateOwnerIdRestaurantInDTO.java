package com.example.restaurants_reviews.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateOwnerIdRestaurantInDTO {

    private Long oldId;
    private Long newId;
}