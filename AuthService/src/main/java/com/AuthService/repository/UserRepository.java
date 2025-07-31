package com.AuthService.repository;

import com.AuthService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmailId(String emailId);
    User findByUsername(String username);
}