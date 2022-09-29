package com.example.user_service.service.impl;

import com.example.user_service.DAO.RoleRepository;
import com.example.user_service.DAO.UserRepository;
import com.example.user_service.DAO.UserRolesRepository;
import com.example.user_service.DTO.in.*;
import com.example.user_service.DTO.out.DeleteOwnerInRestaurantOutDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.entity.Status;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.entity.UserRolesEntity;
import com.example.user_service.exception.PasswordsDontMatchException;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.exception.UserEmailIsAlreadyExist;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitMessageOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RabbitMessageOperations rabbitTemplate;

    public UserServiceImpl(UserRepository userRepository, UserRolesRepository userRolesRepository,
                           UserMapper userMapper, RoleRepository roleRepository,
                           RabbitMessageOperations rabbitTemplate) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.rabbitTemplate = rabbitTemplate;
        ;
    }

    @Override
    public UserOutDTO createUser(UserInDTO userInDTO) throws UserEmailIsAlreadyExist {
        if (userRepository.existsByEmail(userInDTO.getEmail())) {
            throw new UserEmailIsAlreadyExist();
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        userInDTO.setStatus(Status.ACTIVE);
        userInDTO.setPassword(passwordEncoder.encode(userInDTO.getPassword()));
        return userMapper
                .userEntityToUserOutDTO(userRepository.save(userMapper
                        .UserInDTOToUserEntity(userInDTO)));
    }

    @Override
    @Transactional
    public UserOutDTO updateUser(UpdateUserInDTO userInDTO, Long id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        optionalUser.get().setName(userInDTO.getName());
        optionalUser.get().setSurname(userInDTO.getSurname());
        optionalUser.get().setLastname(userInDTO.getLastname());
        optionalUser.get().setPassword(userInDTO.getPassword());
        return userMapper
                .userEntityToUserOutDTO(userRepository.save(optionalUser.get()));
    }

    @Override
    @Transactional
    public Long deleteUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        rabbitTemplate.convertAndSend("queueForDeleteOwner", new DeleteOwnerInRestaurantOutDTO(id));
        userRepository.deleteById(id);
        return id;
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

    @Override
    @Transactional
    public void addRoleToUser(AddRoleToUserInDTO addRoleToUserInDTO) throws RoleNotFoundException,
            UserNotFoundException {
        Optional<RoleEntity> role = roleRepository.findById(addRoleToUserInDTO.getRoleId());
        Optional<UserEntity> user = userRepository.findById(addRoleToUserInDTO.getUserId());
        if (role.isEmpty()) {
            throw new RoleNotFoundException();
        } else if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        UserRolesEntity userRolesEntity = new UserRolesEntity();
        userRolesEntity.setUserId(addRoleToUserInDTO.getUserId());
        userRolesEntity.setRoleId(addRoleToUserInDTO.getRoleId());
        userRolesRepository.save(userRolesEntity);
    }

    @Override
    @Transactional
    public void deleteRoleFromUserByUserRolesId(Long id) {
        userRolesRepository.deleteById(id);
    }

    @Override
    public Long changeUserFromRestaurant(ChangeUserFromRestaurantInDTO changeUserFromRestaurantInDTO)
            throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(changeUserFromRestaurantInDTO.getNewUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        rabbitTemplate.convertAndSend("queueForUpdateOwner", changeUserFromRestaurantInDTO);
        return changeUserFromRestaurantInDTO.getNewUserId();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByName(username);
    }
}
