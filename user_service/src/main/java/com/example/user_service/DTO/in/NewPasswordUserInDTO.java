package com.example.user_service.DTO.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NewPasswordUserInDTO {

    @NotBlank(message = "Cannot be null")
    private String email;

    private String oldPassword;

    @Size(min = 5, message = "Minimum size 5 symbols")
    @NotBlank(message = "Cannot be null")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%#&*@]).{5,}$",
            message = "Password dont match with regexp")
    private String newPassword;
}
