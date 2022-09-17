package com.example.user_service.DTO.in;

import lombok.Data;

@Data
public class ChangeUserFromRestaurantInDTO {

    private Long oldUserId;
    private Long newUserId;
}
