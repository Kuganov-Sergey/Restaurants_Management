package com.example.user_service.security.security_entity;

import com.example.user_service.entity.RoleEntity;
import com.example.user_service.entity.Status;
import com.example.user_service.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {

    public UserFactory() {
    }

    public static SecurityUser create(UserEntity user) {
        return new SecurityUser(user.getName(), user.getPassword(), mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
                , user.getStatus().equals(Status.ACTIVE));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRole())
                ).collect(Collectors.toList());
    }
}