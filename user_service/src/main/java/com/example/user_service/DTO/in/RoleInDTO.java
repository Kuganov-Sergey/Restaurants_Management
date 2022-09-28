package com.example.user_service.DTO.in;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Tag(name = "role", description = "create new role")
public class RoleInDTO {

    private String role;
}
