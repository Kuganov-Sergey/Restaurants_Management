package com.example.user_service.DAO;

import com.example.user_service.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsById(Long id);
}
