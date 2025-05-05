package com.duoc.week3.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duoc.week3.model.Role;
import com.duoc.week3.model.User;
import com.duoc.week3.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1L)
                .name("name")
                .email("email")
                .password("password")
                .phone("+56912345678")
                .birthDate(LocalDate.of(2000, 1, 1))
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .role(Role.builder().id(1L).name("ROLE_USER").build())
                .pets(null)
                .build();
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        when(userService.getAll()).thenReturn(List.of(user));

        // Act
        ResponseEntity<CollectionModel<EntityModel<User>>> response = userController.getAllUsers();
        CollectionModel<EntityModel<User>> foundUsersModel = response.getBody();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, foundUsersModel.getContent().size());
        assertNotNull(foundUsersModel);
        assertTrue(foundUsersModel.hasLink("self"));
        verify(userService).getAll();
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        when(userService.getById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<EntityModel<User>> response = userController.getUserById(userId);
        EntityModel<User> foundUserModel = response.getBody();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(foundUserModel);
        assertEquals(user.getId(), foundUserModel.getContent().getId());
        assertTrue(foundUserModel.hasLink("self"));
        verify(userService).getById(1L);
    }

    @Test
    void testCreateUser() {
        // Arrange
        when(userService.saveUser(user)).thenReturn(user);

        // Act
        ResponseEntity<EntityModel<User>> response = userController.createUser(user);
        EntityModel<User> savedUserModel = response.getBody();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(savedUserModel);
        assertEquals(user.getId(), savedUserModel.getContent().getId());
        assertTrue(savedUserModel.hasLink("self"));
        verify(userService).saveUser(any(User.class));
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        when(userService.updateUser(userId, user)).thenReturn(user);

        // Act
        ResponseEntity<EntityModel<User>> response = userController.updateUser(userId, user);
        EntityModel<User> updatedUserModel = response.getBody();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(updatedUserModel);
        assertEquals(user.getId(), updatedUserModel.getContent().getId());
        assertTrue(updatedUserModel.hasLink("self"));

    }

    @Test
    void testDeleteUser() {
        // Arrange
        Long userId = user.getId();
        doNothing().when(userService).deleteUser(userId);

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        verify(userService).deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
