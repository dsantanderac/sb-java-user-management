package com.duoc.week3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.week3.model.User;

// Repository layer for User
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Retrieve all users
    List<User> findAll();

    User findById(int id);
}
