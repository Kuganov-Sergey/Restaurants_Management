package com.example.user_service.DTO.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserInDTO {

    @NotBlank(message = "The name cannot be empty")
    private String name;

    @NotBlank(message = "The surname cannot be empty")
    private String surname;

    @NotBlank(message = "The lastname cannot be empty")
    private String lastname;

    @NotBlank(message = "Password cannot be null")
    private String password;
}
