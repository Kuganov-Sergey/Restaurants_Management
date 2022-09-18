package com.example.user_service.controller;

import com.example.user_service.DTO.in.AddRoleToUserInDTO;
import com.example.user_service.DTO.in.UpdateUserInDTO;
import com.example.user_service.UserServiceApplicationTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class UserControllerTest extends UserServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deleteUser() throws Exception {
        this.mockMvc.perform(delete("/user/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        this.mockMvc.perform(get("/user/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.surname").value("test"))
                .andExpect(jsonPath("$.lastname").value("test"))
                .andExpect(jsonPath("$.phoneNumber").value("+79998887766"));
    }

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateUser() throws Exception {
        UpdateUserInDTO userInDTO = UpdateUserInDTO.builder()
                .name("test")
                .surname("test")
                .lastname("test")
                .phoneNumber("+9657775533")
                .password("12345@Test")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(userInDTO);
        this.mockMvc.perform(put("/user/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addRoleToUser() throws Exception {
        AddRoleToUserInDTO addRoleToUserInDTO = AddRoleToUserInDTO.builder()
                .roleId(1L)
                .userId(1L)
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(addRoleToUserInDTO);
        this.mockMvc.perform(put("/user/role").contentType(MediaType.APPLICATION_JSON).content(obj));
        //TODO доделать перед после добавиления роли
    }
//
//    @Test
//    void changePasswordByUserEmailAndOldPassword() {
//    }
//
//    @Test
//    void deleteRoleFromUserByUserRolesId() {
//    }
//
//    @Test
//    void changeUserFromRestaurant() {
//    }
}