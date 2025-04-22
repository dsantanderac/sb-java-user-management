package com.duoc.week3.service;

import java.util.List;
import java.util.Optional;

import com.duoc.week3.model.User;

// Service layer for User
public interface UserService {
    List<User> getAll();

    Optional<User> getById(Long id);

    User saveUser(User user);
}
