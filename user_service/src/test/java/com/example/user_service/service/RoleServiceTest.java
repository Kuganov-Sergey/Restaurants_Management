package com.example.user_service.service;

import com.example.user_service.UserServiceApplicationTests;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.service.RoleServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest extends UserServiceApplicationTests {

    @Autowired
    private RoleServiceI roleService;

    @Test
    void createRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("user");
        RoleEntity role = roleService.createRole(roleEntity);
        assertEquals(roleEntity, role);
    }

    @Test
    void deleteRole() throws RoleNotFoundException {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("user");
        RoleEntity role = roleService.createRole(roleEntity);
        assertEquals(roleService.deleteRole(role.getId()), role.getId());
    }
}