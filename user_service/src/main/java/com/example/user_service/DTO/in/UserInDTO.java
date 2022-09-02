package com.example.user_service.DTO.in;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserInDTO {

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The surname cannot be empty")
    private String surname;

    @NotBlank(message = "The lastname cannot be empty")
    private String lastname;

    @Email(message = "Email invalid format")
    private String email;

    @NotBlank(message = "Password cannot be null")
    private String password;
}
