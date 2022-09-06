package com.example.user_service.DTO.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddRoleToUserOutDTO {

    Long userId;
    Long roleId;
}
