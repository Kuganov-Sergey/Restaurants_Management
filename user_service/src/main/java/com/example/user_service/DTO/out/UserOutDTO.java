package com.example.user_service.DTO.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserOutDTO {

    private Long id;
    private String name;
    private String surname;
    private String lastname;
    private String email;

    @EqualsAndHashCode.Exclude
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDateTime registration_date;
}
