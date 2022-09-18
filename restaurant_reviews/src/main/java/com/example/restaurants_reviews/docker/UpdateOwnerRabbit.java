package com.example.restaurants_reviews.docker;

import com.example.restaurants_reviews.dto.out.DeleteOwnerInRestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateOwnerIdRestaurantOutDTO;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class UpdateOwnerRabbit {

    private final RestaurantService restaurantService;

    public UpdateOwnerRabbit(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RabbitListener(queues = "queueForDeleteOwner")
    public void deleteOwnerFromAllRestaurants(@Payload DeleteOwnerInRestaurantOutDTO deleteOwnerInRestaurantOutDTO) {
        restaurantService.deleteOwnerFromAllRestaurants(deleteOwnerInRestaurantOutDTO);
    }

    @RabbitListener(queues = "queueForUpdateOwner")
    public void updateOwnerInAllRestaurants(@Payload UpdateOwnerIdRestaurantOutDTO updateOwnerIdRestaurantOutDTO)
            throws RestaurantNotFoundException, OwnerNotFoundException {
        restaurantService.updateOwnerInAllRestaurants(updateOwnerIdRestaurantOutDTO);
    }
}
