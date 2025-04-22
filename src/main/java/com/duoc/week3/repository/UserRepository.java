package com.duoc.week3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.week3.model.User;

// Repository layer for User
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find a user by email and password
    User findByEmailAndPassword(String email, String password);
}
