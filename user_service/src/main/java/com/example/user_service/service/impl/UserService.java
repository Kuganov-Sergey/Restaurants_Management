package com.example.user_service.service.impl;

import com.example.user_service.DAO.UserRepository;
import com.example.user_service.DTO.in.NewPasswordUserInDTO;
import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.service.UserServiceI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist {
        if (userRepository.existsByEmail(userInDTO.getEmail())) {
            throw new UserEmailIsAlreadyExist();
        }
        return userMapper
                .userEntityToUserOutDTO(userRepository.save(userMapper
                        .UserInDTOToUserEntity(userInDTO)));
    }

    @Override
    @Transactional
    public UserOutDTO updateUser(UserEntity userEntity, Long id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        userEntity.setId(id);
        userEntity.setRegistration_date(optionalUser.get().getRegistration_date());
        userEntity.setEmail(optionalUser.get().getEmail());
        userEntity.setPassword(optionalUser.get().getPassword());
        return userMapper
                .userEntityToUserOutDTO(userRepository.save(userEntity));
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(optionalUser.get());
        return optionalUser.get().getId();
    }

    @Override
    public UserOutDTO getUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userMapper.userEntityToUserOutDTO(optionalUser.get());
    }

    @Override
    public Page<UserEntity> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void newPassword(NewPasswordUserInDTO newPasswordUserInDTO) throws PasswordsDontMatchException {
        UserEntity user = userRepository.findByEmail(newPasswordUserInDTO.getEmail());
        if (!Objects.equals(user.getPassword(), newPasswordUserInDTO.getOldPassword())) {
            throw new PasswordsDontMatchException();
        }
        user.setPassword(newPasswordUserInDTO.getNewPassword());
    }
}
