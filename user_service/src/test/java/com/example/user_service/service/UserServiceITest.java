package com.example.user_service.service;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.UserServiceApplicationTests;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceITest extends UserServiceApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Test
    void createUser() throws UserEmailIsAlreadyExist, UserNotFoundException {
        UserInDTO userInDTO = UserInDTO.builder()
                .email("dodo@mail.ru")
                .name("toto")
                .surname("toto")
                .lastname("toto")
                .password("aA@1tt")
                .build();
        UserOutDTO user = userService.createUser(userInDTO);
        assertEquals(user, userService.getUser(user.getId()));
    }

    @Test
    void updateUser() throws UserEmailIsAlreadyExist, UserNotFoundException {
        UserInDTO userInDTO = UserInDTO.builder()
                .email("toto@mail.ru")
                .name("toto")
                .surname("toto")
                .lastname("toto")
                .password("aA@1tt")
                .build();
        UserOutDTO user = userService.createUser(userInDTO);
        UserEntity userEntity = new UserEntity();
        userEntity.setName("gogo");
        userEntity.setLastname("gogo");
        userEntity.setSurname("gogo");
        UserOutDTO userOutDTO = userService.updateUser(userEntity, user.getId());
        assertEquals(userOutDTO.getName(), userEntity.getName());
        assertEquals(userOutDTO.getLastname(), userEntity.getLastname());
        assertEquals(userOutDTO.getSurname(), userEntity.getSurname());
        assertEquals(userOutDTO.getId(), userEntity.getId());
        assertEquals(userOutDTO.getEmail(), userEntity.getEmail());
        assertEquals(userOutDTO.getRegistration_date(), userEntity.getRegistration_date());
    }

    @Test
    void deleteUser() throws UserEmailIsAlreadyExist, UserNotFoundException {
        UserInDTO userInDTO = UserInDTO.builder()
                .email("bobo@mail.ru")
                .name("toto")
                .surname("toto")
                .lastname("toto")
                .password("aA@1tt")
                .build();
        UserOutDTO user = userService.createUser(userInDTO);
        Long userId = userService.deleteUser(user.getId());
        assertEquals(userId, user.getId());
    }
}