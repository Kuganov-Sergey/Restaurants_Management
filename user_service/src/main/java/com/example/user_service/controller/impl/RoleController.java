package com.example.user_service.controller.impl;

import com.example.user_service.controller.RoleControllerI;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.service.RoleServiceI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController implements RoleControllerI {

    private final RoleServiceI roleService;

    public RoleController(RoleServiceI roleService) {
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
