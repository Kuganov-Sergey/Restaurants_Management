package com.example.restaurants_reviews;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RestaurantsReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsReviewsApplication.class, args);
    }
}
