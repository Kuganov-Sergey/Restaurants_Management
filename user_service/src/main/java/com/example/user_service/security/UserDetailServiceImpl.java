package com.example.user_service.security;

import com.example.user_service.entity.UserEntity;
import com.example.user_service.security.security_entity.SecurityUser;
import com.example.user_service.security.security_entity.UserFactory;
import com.example.user_service.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;

    public UserDetailServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found in JWTUserDetailsService");
        }
         SecurityUser securityUser = UserFactory.create(user);
        log.info("in loadUserByUsername - user with username: {} successfully loaded", username);
        return securityUser;
    }


}
