package com.example.user_service.service;

import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;

public interface RoleServiceI {

    RoleEntity createRole(RoleEntity roleEntity);
    Long deleteRole(Long id) throws RoleNotFoundException;
}
