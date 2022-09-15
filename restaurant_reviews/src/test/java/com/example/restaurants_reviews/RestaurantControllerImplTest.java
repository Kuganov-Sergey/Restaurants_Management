package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.in.UpdateRestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.UserOutDTO;
import com.example.restaurants_reviews.entity.KitchenTypeE;
import com.example.restaurants_reviews.feign_clients.UserServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class RestaurantControllerImplTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Autowired
    private UserServiceClient userServiceClient;

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
        RestaurantInDTO restaurant2 = RestaurantInDTO.builder()
                .description("test2")
                .phoneNumber("+79995555555")
                .emailAddress("test2@mail.ru")
                .date(LocalDate.of(2002, 12, 12))
                .name("test2")
                .kitchenTypeE(KitchenTypeE.ASIAN)
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());
        String obj2 = objectMapper.writeValueAsString(restaurant2);
        this.mockMvc.perform(post("/restaurant")
                        .contentType(MediaType.APPLICATION_JSON).content(obj2))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRestaurantById() throws Exception {
        this.mockMvc.perform(delete("/restaurant/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

//    @Test
//    void getSmallListRestaurants() {
//
//    }

    @Test
    void updateRestaurantById() throws Exception {
        UpdateRestaurantInDTO restaurantInDTO = UpdateRestaurantInDTO.builder()
                .description("test")
                .emailAddress("test@mail.ru")
                .ownerId(1L)
                .name("test")
                .kitchenTypeE(KitchenTypeE.ASIAN)
                .phoneNumber("+79996665522")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurantInDTO);
        Mockito.when(userServiceClient.getUser(1L)).thenReturn(ResponseEntity.accepted()
                        .contentType(MediaType.APPLICATION_JSON).body(new UserOutDTO()));
        this.mockMvc.perform(put("/restaurant/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getRestaurantById() throws Exception {
        this.mockMvc.perform(get("/restaurant/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.description").value("test"))
                .andExpect(jsonPath("$.emailAddress").value("test@mail.ru"))
                .andExpect(jsonPath("$.phoneNumber").value("+79996665522"))
                .andExpect(jsonPath("$.kitchenTypeE").value("ASIAN"));
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/restaurant"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
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
