package com.example.user_service.DTO.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChangeUserFromRestaurantInDTO {

    private Long oldUserId;
    private Long newUserId;
}
