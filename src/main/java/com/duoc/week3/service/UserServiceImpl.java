package com.duoc.week3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.week3.model.Role;
import com.duoc.week3.model.User;
import com.duoc.week3.repository.RoleRepository;
import com.duoc.week3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Service layer to manage business logic
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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

    // Override update user method with the business logic
    @Override
    public User updateUser(Long id, User user) {
        logger.info("Update user with ID: {} - method updateUser", id);
        try {
            Optional<User> existingUserOpt = userRepository.findById(id);

            if (existingUserOpt.isPresent()) {
                User updatedUser = existingUserOpt.get();

                updatedUser.setName(user.getName());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setPassword(user.getPassword());
                updatedUser.setPhone(user.getPhone());
                updatedUser.setBirthDate(user.getBirthDate());
                updatedUser.setUpdated_at(LocalDateTime.now());

                // Reasignar role solo si lo mandas en el request
                if (user.getRole() != null && user.getRole().getId() != null) {
                    Role role = roleRepository.findById(user.getRole().getId())
                            .orElseThrow(() -> new RuntimeException("Role not found"));
                    updatedUser.setRole(role);
                }

                return userRepository.save(updatedUser);
            } else {
                logger.warn("User with ID: {} not found - method updateUser", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Update user error - method updateUser", e);
            throw e;
        }
    }
}
