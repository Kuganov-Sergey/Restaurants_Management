package com.example.user_service.service.impl;

import com.example.user_service.DAO.RoleRepository;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.service.RoleServiceI;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceI {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity createRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public Long deleteRole(Long id) throws RoleNotFoundException {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException();
        }
        roleRepository.deleteById(id);
        return id;
    }
}
