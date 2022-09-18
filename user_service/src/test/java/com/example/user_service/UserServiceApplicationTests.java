package com.example.user_service;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.entity.RoleEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        UserServiceApplication.class})
@ActiveProfiles("test")
public class UserServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static int counterForEmail = 1;

    @BeforeEach
    void beforeAddObjects() throws Exception {
        UserInDTO userInDTO = UserInDTO.builder()
                .name("test")
                .surname("test")
                .lastname("test")
                .password("12345@Test")
                .email("test" + counterForEmail++ + "@mail.ru")
                .phoneNumber("+79998887766")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(userInDTO);
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON).content(obj))
                .andExpect(status().isOk());

        UserInDTO userInDTO2 = UserInDTO.builder()
                .name("test- 2")
                .surname("test- 2")
                .lastname("test- 2")
                .password("12345@Test")
                .email("test" + counterForEmail++ + "@mail.ru")
                .phoneNumber("+79998887700")
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj2 = objectMapper.writeValueAsString(userInDTO2);
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON).content(obj2))
                .andExpect(status().isOk());

        //TODO добавить новую роль после добавления роль дто
    }
}
