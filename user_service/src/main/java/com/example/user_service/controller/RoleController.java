package com.example.user_service.controller;

import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "role", description = "The role API")
public interface RoleController {


    //TODO изменть на роль ДТО
    @Operation(summary = "Create new role")
    @PostMapping
    RoleEntity createRole(@RequestBody RoleEntity roleEntity);

    @Operation(summary = "Delete role by id")
    @DeleteMapping("/{id}")
    Long deleteRoleById(@PathVariable Long id) throws RoleNotFoundException;
}
