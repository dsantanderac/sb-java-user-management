package com.duoc.week3.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.duoc.week3.model.User;
import com.duoc.week3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Service layer to manage business logic
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    // Override get all users method with the business logic
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Override get user by id method with the business logic
    @Override
    public User getById(int id) {
        return userRepository.findById(id);
    }

}
