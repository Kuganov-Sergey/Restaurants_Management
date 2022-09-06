package com.example.user_service.controller;

import com.example.user_service.DTO.in.NewPasswordUserInDTO;
import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
public interface UserControllerI {

    @PostMapping("/create")
    UserOutDTO createUser(@RequestBody @Valid UserInDTO userInDTO) throws UserEmailIsAlreadyExist;


    @PutMapping("/update/{id}")
    UserOutDTO updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) throws UserNotFoundException;

    @DeleteMapping("/delete/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    @DeleteMapping("/delete/{id}/{newUserId}")
    Long deleteAndUpdateUser(@PathVariable Long id, @PathVariable Long newUserId) throws UserNotFoundException;

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/get/{id}")
    UserOutDTO getUser(@PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Get all users")
    @GetMapping("/get")
    Page<UserEntity> getAll(Pageable pageable);

    @PutMapping("/new_password")
    void newPassword(@RequestBody @Valid NewPasswordUserInDTO newPasswordUserInDTO) throws PasswordsDontMatchException;

    @PutMapping("/get_role/{userId}/{roleId}")
    void addRoleToUser(@PathVariable Long userId,@PathVariable Long roleId);

    @DeleteMapping("/delete_role/{userId}/{roleId}")
    void deleteRoleByUserIdAndRoleId(@PathVariable Long userId, @PathVariable Long roleId);
}
