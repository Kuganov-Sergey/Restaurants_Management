package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.RestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
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
public class RestaurantControllerTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        this.mockMvc.perform(get("/restaurant/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void descriptionByName() throws Exception {
        String expected = restaurantService.getDescriptionByName("mac");
        this.mockMvc.perform(get("/restaurant/description/{name}", "mac"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    void addRestaurantByNameAndCreationDate() throws FoundationDateIsExpiredException, RestaurantNotFoundException {
        MockedStatic<LocalDate> localDateMockedStatic = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
        LocalDate defaultDateNow = LocalDate.of(2012, 2, 2);
        localDateMockedStatic.when(LocalDate::now).thenReturn(defaultDateNow);
        assertThrows(FoundationDateIsExpiredException.class,
                () -> restaurantService
                        .addRestaurantByNameAndCreationDate("mac",
                                LocalDate.of(2015, 2, 2)));

        LocalDate localDateExpected = LocalDate.of(2012, 2, 2);
        restaurantService.addRestaurantByNameAndCreationDate("kfc", localDateExpected);
        assertEquals(localDateExpected, restaurantService.getCreationDateByRestaurantName("kfc"));
    }

    @Test
    void addRestaurants() throws Exception {
        RestaurantInDTO restaurant = RestaurantInDTO.builder()
                .description("burgers")
                .phoneNumber("+79996665522")
                .emailAddress(null)
                .date(LocalDate.of(2000, 10, 10))
                .name("mmm")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(post("/restaurant/new")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());
    }

    @Test
    void updateDescription() throws Exception {
        this.mockMvc.perform(put("/restaurant/update/{name}/{description}", "mac", "description"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findRestaurantByName() throws Exception {
        RestaurantOutDTO restaurant = RestaurantOutDTO.builder()
                .id(restaurantService.getAllRestaurants(Pageable.unpaged()).toList().get(0).getId())
                .description("burgers")
                .phoneNumber("+79997771122")
                .emailAddress(null)
                .name("mac")
                .build();
        String expected = objectMapper.writeValueAsString(restaurant);
        this.mockMvc.perform(get("/restaurant/{name}", "mac"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
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
        this.mockMvc.perform(post("/restaurant/new")
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

    @Test
    public void testSortingGetAllRestaurantsPaging() {

    }
}
