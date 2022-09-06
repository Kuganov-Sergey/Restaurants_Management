package com.example.user_service.DTO.out;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateOwnerIdRestaurantOutDTO {

    @Schema(description = "Old owner id")
    private Long oldId;

    @Schema(description = "New owner id")
    private Long newId;
}
