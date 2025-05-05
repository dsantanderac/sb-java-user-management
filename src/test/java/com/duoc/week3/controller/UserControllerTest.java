package com.duoc.week3.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        when(userService.getById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<User> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testCreateUser() {
        // Arrange
        when(userService.saveUser(user)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.createUser(user);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        when(userService.updateUser(userId, user)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.updateUser(userId, user);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
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
