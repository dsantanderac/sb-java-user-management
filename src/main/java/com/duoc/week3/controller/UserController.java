package com.duoc.week3.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.week3.model.LoginRequest;
import com.duoc.week3.model.User;
import com.duoc.week3.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    // Dependency Injection
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        logger.info("Retrieved {} users", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return user.map(value -> {
            logger.info("User found by ID: {}", id);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }).orElseGet(() -> {
            logger.info("User not found by ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating a new user with request: {}", user);
        User savedUser = userService.saveUser(user);
        logger.info("User created successfully. User ID: {}", savedUser.getId());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("Updating user with ID: {} and request: {}", id, user);
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            logger.info("User not found for update with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("User updated successfully. User ID: {}", updatedUser.getId());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        logger.info("User deleted successfully. User ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest user) {
        logger.info("Logging in user with email: {}", user.getEmail());
        User loggedInUser = userService.loginUser(user);
        if (loggedInUser == null) {
            logger.info("Login failed for email: {}", user.getEmail());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.info("User logged in successfully. User ID: {}", loggedInUser.getId());
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
}
