package com.example.restaurants_reviews.feign_clients;

import com.example.restaurants_reviews.dto.out.UserOutDTO;
import com.example.restaurants_reviews.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userServiceClient", url = "${my.userserviceclient.url}", decode404 = true,
        configuration = FeignClientConfig.class)
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}",
            consumes = "application/json", produces = "application/json")
    ResponseEntity<UserOutDTO> getUser(@PathVariable(value = "userId") Long userId);
}
