package com.example.user_service.controller.impl;

import com.example.user_service.DTO.in.*;
import com.example.user_service.DTO.out.DeleteOwnerInRestaurantOutDTO;
import com.example.user_service.DTO.out.UpdateOwnerIdRestaurantOutDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.controller.UserControllerI;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.service.impl.UserService;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

public class UserController implements UserControllerI {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist {
        return userService.createUser(userInDTO);
    }

    @Override
    public UserOutDTO updateUser(UpdateUserInDTO userInDTO, Long id) throws UserNotFoundException {
        return userService.updateUser(userInDTO, id);
    }

    @Override
    public Long changeUserFromRestaurant(ChangeUserFromRestaurantInDTO changeUserFromRestaurantInDTO)
            throws UserNotFoundException {
        return userService.changeUserFromRestaurant(changeUserFromRestaurantInDTO);
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }

    @Override
    public UserOutDTO getUser(Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @Override
    public void changePasswordByUserEmailAndOldPassword(NewPasswordUserInDTO newPasswordUserInDTO)
            throws PasswordsDontMatchException {
        userService.newPassword(newPasswordUserInDTO);
    }

    @Override
    public void addRoleToUser(AddRoleToUserInDTO addRoleToUserInDTO) throws UserNotFoundException,
            RoleNotFoundException {
        userService.addRoleToUser(addRoleToUserInDTO);
    }

    @Override
    public void deleteRoleFromUserByUserRolesId(Long userId) {
        userService.deleteRoleFromUserByUserRolesId(userId);
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
