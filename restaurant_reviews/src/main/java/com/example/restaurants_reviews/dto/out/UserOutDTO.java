package com.example.restaurants_reviews.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Tag(name = "user", description = "dto for check exist user")
public class UserOutDTO {

    @Schema(description = "user id")
    private Long id;

    @Schema(description = "user name")
    private String name;

    @Schema(description = "user surname")
    private String surname;

    @Schema(description = "user lastname")
    private String lastname;

    @Schema(description = "user email address")
    private String email;

    @Schema(description = "Date of registration user")
    @EqualsAndHashCode.Exclude
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate registration_date;
}
