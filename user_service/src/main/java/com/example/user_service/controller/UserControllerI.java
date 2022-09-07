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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "user", description = "The user API")
@RequestMapping("/user")
public interface UserControllerI {

    @Operation(summary = "Create new user")
    @PostMapping
    UserOutDTO createUser(@RequestBody @Valid UserInDTO userInDTO) throws UserEmailIsAlreadyExist;

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    UserOutDTO updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    //TODO не правильно, нужно додумать
    @DeleteMapping("/{id}/{newUserId}")
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
    @GetMapping("/{id}")
    UserOutDTO getUser(@PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Get all users")
    @GetMapping
    Page<UserEntity> getAll(Pageable pageable);

    @Operation(summary = "Create new password for user")
    @PutMapping("/new_password")
    void newPassword(@RequestBody @Valid NewPasswordUserInDTO newPasswordUserInDTO) throws PasswordsDontMatchException;

    @Operation(summary = "Add role to user")
    @PutMapping("/{userId}/{roleId}")
    void addRoleToUser(@PathVariable Long userId,@PathVariable Long roleId);

    @Operation(summary = "Delete role by user.id and role.id")
    @DeleteMapping("/{userId}/{roleId}")
    void deleteRoleByUserIdAndRoleId(@PathVariable Long userId, @PathVariable Long roleId);
}
