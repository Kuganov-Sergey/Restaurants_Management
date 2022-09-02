package com.example.user_service.DAO;

import com.example.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
