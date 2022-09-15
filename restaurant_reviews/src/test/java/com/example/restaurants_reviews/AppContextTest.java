package com.example.restaurants_reviews;

import com.example.restaurants_reviews.feign_clients.UserServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {
        RestaurantsReviewsApplication.class})
@ActiveProfiles("test")
public class AppContextTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    public UserServiceClient userServiceClientMock() {
//        return Mockito.mock(UserServiceClient.class);
//    }
}