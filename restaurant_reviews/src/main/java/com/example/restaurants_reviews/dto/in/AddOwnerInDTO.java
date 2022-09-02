package com.example.restaurants_reviews.dto.in;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddOwnerInDTO {

//    @NotBlank(message = "Empty id")
//    private Long id;

    @NotBlank(message = "Empty id")
    private String email;

    @NotBlank(message = "Empty id")
    private String restaurantName;
}
