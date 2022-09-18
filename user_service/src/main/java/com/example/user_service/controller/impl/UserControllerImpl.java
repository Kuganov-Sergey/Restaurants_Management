package com.example.user_service.controller.impl;

import com.example.user_service.DTO.in.*;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.controller.UserController;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class UserControllerImpl implements UserController {

    private final UserServiceImpl userServiceImpl;

    public UserControllerImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist {
        return userServiceImpl.createUser(userInDTO);
    }

    @Override
    public UserOutDTO updateUser(UpdateUserInDTO userInDTO, Long id) throws UserNotFoundException {
        return userServiceImpl.updateUser(userInDTO, id);
    }

    @Override
    public Long changeUserFromRestaurant(ChangeUserFromRestaurantInDTO changeUserFromRestaurantInDTO)
            throws UserNotFoundException {
        return userServiceImpl.changeUserFromRestaurant(changeUserFromRestaurantInDTO);
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        return userServiceImpl.deleteUser(id);
    }

    @Override
    public UserOutDTO getUserById(Long id) throws UserNotFoundException {
        return userServiceImpl.getUser(id);
    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable) {
        return userServiceImpl.getAll(pageable);
    }

    @Override
    public void changePasswordByUserEmailAndOldPassword(NewPasswordUserInDTO newPasswordUserInDTO)
            throws PasswordsDontMatchException {
        userServiceImpl.newPassword(newPasswordUserInDTO);
    }

    @Override
    public void addRoleToUser(AddRoleToUserInDTO addRoleToUserInDTO) throws UserNotFoundException,
            RoleNotFoundException {
        userServiceImpl.addRoleToUser(addRoleToUserInDTO);
    }

    @Override
    public void deleteRoleFromUserByUserRolesId(Long userId) {
        userServiceImpl.deleteRoleFromUserByUserRolesId(userId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleArgumentFormatException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
