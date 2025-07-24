package com.smartcart.user_service.service;

import com.smartcart.user_service.dto.User;
import com.smartcart.user_service.entity.UserEntity;

import java.util.List;

public interface UserService {

    User createUser(User createUser);

    UserEntity updateUser(Long id, User user);

  //  void deleteUser(Long id);

    void softDeleteUser(Long id);

    void restoreUser(Long id);

    void deleteUser(Long id);

    boolean emailExists(String email);

    UserEntity getUserById(Long id);

    User getUserByEmail(String email);

    List<UserEntity> filterUsersByRole(String role);

    List<UserEntity> searchUsers(String keyword);

    long countUsers();
}
