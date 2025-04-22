package com.duoc.week3.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duoc.week3.model.User;
import com.duoc.week3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Service layer to manage business logic
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // Override get all users method with the business logic
    @Override
    public List<User> getAll() {
        logger.info("Finding all users - method getAll");
        return userRepository.findAll();
    }

    // Override get user by id method with the business logic
    @Override
    public Optional<User> getById(Long id) {
        logger.info("Finding user by ID: {} - method getById", id);
        return userRepository.findById(id);
    }

    // Override save user method with the business logic
    @Override
    public User saveUser(User user) {
        logger.info("Create new user: {} - method saveUser", user);
        try {
            // Set the owner for each pet
            if (user.getPets() != null) {
                user.getPets().forEach(pet -> pet.setOwner(user));
            }

            User savedUser = userRepository.save(user);
            logger.info("User successfully created: {} - method saveUser", savedUser.getId());

            return savedUser;
        } catch (Exception e) {
            logger.error("Create user error - method saveUser", e);
            throw e;
        }
    }
}
