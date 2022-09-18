package com.example.user_service.service;

import com.example.user_service.DTO.in.*;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist;
    UserOutDTO updateUser(UpdateUserInDTO userInDTO, Long id) throws UserNotFoundException;
    Long deleteUser(Long id) throws UserNotFoundException;
    UserOutDTO getUser(Long id) throws UserNotFoundException;
    Page<UserEntity> getAll(Pageable pageable);
    void newPassword(NewPasswordUserInDTO newPasswordUserInDTO) throws PasswordsDontMatchException;
    void addRoleToUser(AddRoleToUserInDTO addRoleToUserInDTO) throws RoleNotFoundException, UserNotFoundException;
    void deleteRoleFromUserByUserRolesId(Long userId);
    Long changeUserFromRestaurant(ChangeUserFromRestaurantInDTO changeUserFromRestaurantInDTO) throws UserNotFoundException;
}
