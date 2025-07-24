package com.smartcart.user_service.repository;

import com.smartcart.user_service.dto.User;
import com.smartcart.user_service.entity.Role;
import com.smartcart.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);

   List<UserEntity> findByRole(Role role);

    List<UserEntity> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String keyword, String keyword1);

    Optional<UserEntity> findByEmail(String email);
}
