package com.demo.security.dao;

import com.demo.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
