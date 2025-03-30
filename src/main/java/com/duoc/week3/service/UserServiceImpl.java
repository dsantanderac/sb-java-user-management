package com.duoc.week3.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.duoc.week3.model.User;
import com.duoc.week3.repository.UserRepository;

// Service layer to manage business logic
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository = new UserRepository();

    // Override get all users method with the business logic
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Override get user by id method with the business logic
    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

}
