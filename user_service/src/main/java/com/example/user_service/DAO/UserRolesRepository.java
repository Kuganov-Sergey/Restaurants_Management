package com.example.user_service.DAO;

import com.example.user_service.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {
    void deleteByUserIdAndRoleId(Long userId, Long roleId);
}
