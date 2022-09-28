package com.example.user_service.DTO.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "DTO to update password to User Entity by email address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewPasswordUserInDTO {

    @Schema(description = "User Email")
    @NotBlank(message = "Cannot be null")
    private String email;

    @Schema(description = "Old owner password")
    private String oldPassword;

    @Schema(description = "New password")
    @Size(min = 5, message = "Minimum size 5 symbols")
    @NotBlank(message = "Cannot be null")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%#&*@]).{5,}$",
            message = "Password dont match with regexp")
    private String newPassword;
}
