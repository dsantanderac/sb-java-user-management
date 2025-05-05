package com.duoc.week3.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers() {
        List<EntityModel<User>> users = userService.getAll().stream()
                .map(user -> {
                    EntityModel<User> userModel = EntityModel.of(user);
                    userModel.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());
                    return userModel;
                }).toList();
        logger.info("Retrieved {} users", users.size());

        return ResponseEntity.ok(CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            EntityModel<User> userModel = EntityModel.of(user.get());
            userModel.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());

            return ResponseEntity.ok(userModel);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<EntityModel<User>> createUser(@RequestBody User user) {
        logger.info("Creating a new user with request: {}", user);
        User savedUser = userService.saveUser(user);
        logger.info("User created successfully. User ID: {}", savedUser.getId());
        EntityModel<User> userModel = EntityModel.of(savedUser);
        userModel.add(linkTo(methodOn(UserController.class).getUserById(savedUser.getId())).withSelfRel());

        return ResponseEntity.created(
                linkTo(methodOn(UserController.class).getUserById(savedUser.getId())).toUri())
                .body(userModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("Updating user with ID: {} and request: {}", id, user);
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            logger.info("User not found for update with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("User updated successfully. User ID: {}", updatedUser.getId());

        EntityModel<User> userModel = EntityModel.of(updatedUser);
        userModel.add(linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel());

        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        logger.info("User deleted successfully. User ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<EntityModel<User>> loginUser(@RequestBody LoginRequest user) {
        logger.info("Logging in user with email: {}", user.getEmail());
        User loggedInUser = userService.loginUser(user);
        if (loggedInUser == null) {
            logger.info("Login failed for email: {}", user.getEmail());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        logger.info("User logged in successfully. User ID: {}", loggedInUser.getId());

        EntityModel<User> userModel = EntityModel.of(loggedInUser);
        userModel.add(linkTo(methodOn(UserController.class).getUserById(loggedInUser.getId())).withSelfRel());

        return ResponseEntity.ok(userModel);
    }
}
