package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RestaurantControllerImplTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewService reviewService;

    @Test
    void getAll() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        this.mockMvc.perform(get("/restaurant"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addRestaurant() throws Exception {
        RestaurantInDTO restaurant = RestaurantInDTO.builder()
                .description("burgers")
                .phoneNumber("+79996665522")
                .emailAddress(null)
                .date(LocalDate.of(2000, 10, 10))
                .name("mmm")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());
    }

    @Test
    public void validationTest() throws Exception {
        RestaurantInDTO restaurant = RestaurantInDTO.builder()
                .name(" ")
                .description(" ")
                .emailAddress(" dasdsa")
                .phoneNumber("asdasdas")
                .date(LocalDate.of(2050, 10, 10))
                .build();
        String expected = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(expected))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("""
                        {
                                    "date": "Creation date before the current date",
                                    "emailAddress": "Is not email format",
                                    "phoneNumber": "Invalid format phone number",
                                    "name": "Empty name",
                                    "description": "Empty description"
                        }"""));
    }
}
