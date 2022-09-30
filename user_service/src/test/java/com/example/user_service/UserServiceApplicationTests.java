package com.example.user_service;

import com.example.user_service.DTO.in.AddRoleToUserInDTO;
import com.example.user_service.DTO.in.RoleInDTO;
import com.example.user_service.DTO.in.UserInDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        UserServiceApplication.class})
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/**
 *                   ||
 * Disable security \|/         */
@AutoConfigureMockMvc(addFilters = false)
public class UserServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static int counterForEmail = 1;

    @BeforeAll
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
        String firstUserObject = objectMapper.writeValueAsString(userInDTO);
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON).content(firstUserObject))
                .andExpect(status().isOk());

        UserInDTO userInDTO2 = UserInDTO.builder()
                .name("test- 2")
                .surname("test- 2")
                .lastname("test- 2")
                .password("12345@Test")
                .email("test" + counterForEmail++ + "@mail.ru")
                .phoneNumber("+79998887700")
                .build();
        String secondUserObject = objectMapper.writeValueAsString(userInDTO2);
        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON).content(secondUserObject))
                .andExpect(status().isOk());

        RoleInDTO roleInDTO = RoleInDTO.builder()
                .role("ROLE_USER")
                .build();
        String firstRoleObject = objectMapper.writeValueAsString(roleInDTO);
        this.mockMvc.perform(post("/role")
                        .contentType(MediaType.APPLICATION_JSON).content(firstRoleObject))
                .andExpect(status().isOk());

        RoleInDTO roleInDTO2 = RoleInDTO.builder()
                .role("ROLE_ADMIN")
                .build();
        String secondRoleObject = objectMapper.writeValueAsString(roleInDTO2);
        this.mockMvc.perform(post("/role")
                        .contentType(MediaType.APPLICATION_JSON).content(secondRoleObject))
                .andExpect(status().isOk());

        AddRoleToUserInDTO addRoleToUserInDTO = AddRoleToUserInDTO.builder()
                .roleId(1L)
                .userId(1L)
                .build();
        objectMapper.registerModule(new JavaTimeModule());
        String obj = objectMapper.writeValueAsString(addRoleToUserInDTO);
        this.mockMvc.perform(put("/user/role")
                        .contentType(MediaType.APPLICATION_JSON).content(obj)).andDo(print())
                .andExpect(status().isOk());
    }

}
