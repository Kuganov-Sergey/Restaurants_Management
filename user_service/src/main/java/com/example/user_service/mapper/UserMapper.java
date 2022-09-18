package com.example.user_service.mapper;

import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.in.AddRoleToUserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.entity.UserRolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserOutDTO userEntityToUserOutDTO(UserEntity userEntity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserEntity UserInDTOToUserEntity(UserInDTO userInDTO);
}
