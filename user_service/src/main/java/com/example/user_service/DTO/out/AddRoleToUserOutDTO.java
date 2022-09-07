package com.example.user_service.DTO.out;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Tag(name = "role", description = "dto for add role to user")
public class AddRoleToUserOutDTO {

    @Schema(description = "user id")
    Long userId;

    @Schema(description = "role id")
    Long roleId;
}
