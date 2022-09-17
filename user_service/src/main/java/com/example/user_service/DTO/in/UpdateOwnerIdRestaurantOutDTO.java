package com.example.user_service.DTO.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateOwnerIdRestaurantOutDTO {

    private Long oldId;
    private Long newId;
}