package com.example.restaurants_reviews;

import com.example.restaurants_reviews.dto.in.DeleteOwnerInRestaurantOutDTO;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TestRabbit {

    private final RestaurantService restaurantService;

    public TestRabbit(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RabbitListener(queues = "myQueue")
    private void testRabbit(@Payload DeleteOwnerInRestaurantOutDTO deleteOwnerInRestaurantOutDTO)
            throws OwnerNotFoundException {
        restaurantService.deleteOwner(deleteOwnerInRestaurantOutDTO);
    }
}
