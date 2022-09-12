package com.example.restaurants_reviews.docker;

import com.example.restaurants_reviews.dto.in.UpdateOwnerIdRestaurantInDTO;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
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

    @RabbitListener(queues = "myQueue")
    public void updateOwnerFromUserService(@Payload UpdateOwnerIdRestaurantInDTO updateOwnerIdRestaurantInDTO)
            throws OwnerNotFoundException {
        restaurantService.updateOwner(updateOwnerIdRestaurantInDTO);
    }
}
