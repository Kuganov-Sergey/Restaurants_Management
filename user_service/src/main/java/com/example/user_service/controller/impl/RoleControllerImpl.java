package com.example.user_service.controller.impl;

import com.example.user_service.controller.RoleController;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    public RoleControllerImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleEntity createRole(RoleEntity roleEntity) {
        return roleService.createRole(roleEntity);
    }

    @Override
    public Long deleteRoleById(Long id) throws RoleNotFoundException {
        return roleService.deleteRole(id);
    }
}
