package com.example.user_service.controller;

import com.example.user_service.DTO.in.NewPasswordUserInDTO;
import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface UserControllerI {

    @PostMapping("/create")
    UserOutDTO createUser(@RequestBody @Valid UserInDTO userInDTO) throws UserEmailIsAlreadyExist;


    @PutMapping("/update/{id}")
    UserOutDTO updateUser(@RequestBody UserEntity userEntity, @PathVariable Long id) throws UserNotFoundException;

    @DeleteMapping("/delete/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    @GetMapping("/get/{id}")
    UserOutDTO getUser(@PathVariable Long id) throws UserNotFoundException;

    @GetMapping("/get")
    Page<UserEntity> getAll(Pageable pageable);

    @PutMapping("/new_password")
    void newPassword(@RequestBody @Valid NewPasswordUserInDTO newPasswordUserInDTO) throws PasswordsDontMatchException;
}
