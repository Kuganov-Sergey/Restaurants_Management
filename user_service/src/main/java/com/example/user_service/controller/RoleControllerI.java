package com.example.user_service.controller;

import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleControllerI {

    @PostMapping("/create")
    RoleEntity createRole(@RequestBody RoleEntity roleEntity);

    @DeleteMapping("/delete/{id}")
    Long deleteRoleById(@PathVariable Long id) throws RoleNotFoundException;
}
