package com.example.user_service.service.impl;

import com.example.user_service.DAO.RoleRepository;
import com.example.user_service.DTO.in.RoleInDTO;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.exception.RoleNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public RoleServiceImpl(RoleRepository roleRepository, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public RoleEntity createRole(RoleInDTO roleInDTO) {
        return roleRepository.save(userMapper.roleInDTOToRoleEntity(roleInDTO));
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
