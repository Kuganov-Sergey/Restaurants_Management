package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.UpdateReviewInDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ReviewControllerImplTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getReviewById() throws Exception {
        this.mockMvc.perform(get("/review/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.restaurantId").value(1L))
                .andExpect(jsonPath("$.review").value("test"))
                .andExpect(jsonPath("$.rating").value(4));
    }

    @Test
    void updateReview() throws Exception {
        UpdateReviewInDTO updateReviewInDTO = UpdateReviewInDTO.builder()
                .review("test")
                .rating(4)
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(updateReviewInDTO);
        this.mockMvc.perform(put("/review/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
