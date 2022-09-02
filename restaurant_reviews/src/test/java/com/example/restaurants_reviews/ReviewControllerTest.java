package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.ReviewInDTO;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ReviewControllerTest extends AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void getReviewsByRestaurantName() throws Exception {
        this.mockMvc.perform(get("/review/{name}", "mac"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getRatingByRestaurantName() throws Exception {
        double expected = reviewService.getRatingByRestaurantName("mac");
        this.mockMvc.perform(get("/review/rating/{name}", "mac"))
                .andDo(print())
                .andExpect(content().string(Double.toString(expected)));
    }

    @Test
    void addReview() throws Exception {
        ReviewInDTO review = ReviewInDTO.builder()
                .review("cool burgers")
                .restaurant_id(restaurantService.findRestaurantByName("mac").getId())
                .rating(5)
                .build();
        ObjectMapper objectMapper = new JsonMapper();
        String obj = objectMapper.writeValueAsString(review);
        this.mockMvc.perform(post("/review/new")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());
    }

}
