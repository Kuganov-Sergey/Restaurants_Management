package com.example.user_service.controller.impl;

import com.example.user_service.UserServiceApplicationTests;
import com.example.user_service.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerTest extends UserServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected UserService userService;

    @Test
    void createUser() throws Exception {
    }

    @Test
    void updateUser() throws Exception {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() throws Exception {
//        this.mockMvc.perform(get("/user/get/{1}"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAll() {

    }

    @Test
    void handleArgumentFormatException() {
    }
}