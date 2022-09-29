package com.example.user_service.DTO.in;

import com.example.user_service.constraint.ValidPhoneNumber;
import com.example.user_service.entity.Status;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @ValidPhoneNumber(message = "Invalid format of phone number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status;
}
