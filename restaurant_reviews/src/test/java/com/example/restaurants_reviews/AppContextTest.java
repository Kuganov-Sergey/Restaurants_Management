package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.entity.KitchenTypeE;
import com.example.restaurants_reviews.feign_clients.UserServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        RestaurantsReviewsApplication.class})
@ActiveProfiles("test")
public class AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeAddRestaurant() throws Exception {
        RestaurantInDTO restaurant = RestaurantInDTO.builder()
                .description("test")
                .phoneNumber("+79996665522")
                .emailAddress("test@mail.ru")
                .date(LocalDate.of(2000, 10, 10))
                .name("test")
                .kitchenTypeE(KitchenTypeE.ASIAN)
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());

        RestaurantInDTO restaurant2 = RestaurantInDTO.builder()
                .description("test2")
                .phoneNumber("+79995555555")
                .emailAddress("test2@mail.ru")
                .date(LocalDate.of(2002, 12, 12))
                .name("test2")
                .kitchenTypeE(KitchenTypeE.ASIAN)
                .build();
        String obj2 = objectMapper.writeValueAsString(restaurant2);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(obj2))
                .andExpect(status().isOk());

        ReviewInDTO reviewInDTO = ReviewInDTO.builder()
                .restaurant_id(1L)
                .review("test")
                .rating(4)
                .build();
        String obj3 = objectMapper.writeValueAsString(reviewInDTO);
        this.mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON).content(obj3))
                .andExpect(status().isOk());
    }

//    @Bean
//    public UserServiceClient userServiceClientMock() {
//        return Mockito.mock(UserServiceClient.class);
//    }
}