package com.example.user_service.DTO.out;

import com.example.user_service.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Tag(name = "user", description = "dto for creation of new user")
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
    private LocalDate registrationDate;

    @Schema(description = "Phone number of user")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Collection<RoleEntity> roles;
}
