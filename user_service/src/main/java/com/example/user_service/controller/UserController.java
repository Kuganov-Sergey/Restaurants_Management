package com.example.user_service.controller;

import com.example.user_service.DTO.in.*;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.RoleNotFoundException;
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
public interface UserController {

    @Operation(summary = "Create new user")
    @PostMapping
    UserOutDTO createUser(@RequestBody @Valid UserInDTO userInDTO) throws UserEmailIsAlreadyExist;

    @Operation(summary = "Delete user by id")
    @DeleteMapping("/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

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
    UserOutDTO getUserById(@PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Get all users")
    @GetMapping
    Page<UserEntity> getAll(Pageable pageable);

    @Operation(summary = "Update user by id")
    @PutMapping("/{id}")
    UserOutDTO updateUser(@RequestBody UpdateUserInDTO userInDTO, @PathVariable Long id) throws UserNotFoundException;

    @Operation(summary = "Add role to user")
    @PutMapping("/role")
    void addRoleToUser(@RequestBody AddRoleToUserInDTO addRoleToUserInDTO) throws UserNotFoundException, RoleNotFoundException;

    @Operation(summary = "Create new password for user by email and old password")
    @PutMapping("/password")
    void changePasswordByUserEmailAndOldPassword(@RequestBody @Valid NewPasswordUserInDTO newPasswordUserInDTO) 
            throws PasswordsDontMatchException;

    @Operation(summary = "Delete role by role id")
    @DeleteMapping("/{id}/role")
    void deleteRoleFromUserByUserRolesId(@PathVariable Long id);

    @PutMapping("/changeUser")
    Long changeUserFromRestaurant(@RequestBody ChangeUserFromRestaurantInDTO changeUserFromRestaurantInDTO)
            throws UserNotFoundException;
}
