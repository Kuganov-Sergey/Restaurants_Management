package com.example.user_service.service;

import com.example.user_service.DTO.in.RoleInDTO;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;

public interface RoleService {

    RoleEntity createRole(RoleInDTO roleInDTO);
    Long deleteRole(Long id) throws RoleNotFoundException;
}
