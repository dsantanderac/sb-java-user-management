
package com.duoc.week3.repository;

import com.duoc.week3.model.Role;
import com.duoc.week3.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void tetsFindByEmailAndPassword() {
        // Arrange
        User user = User.builder()
                .name("name")
                .email("email@mail.com")
                .password("password")
                .phone("+56912345678")
                .birthDate(LocalDate.of(2000, 1, 1))
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .role(Role.builder().id(2L).name("PET_FRIENDLY_DRIVER").build())
                .pets(null)
                .build();

        userRepository.save(user);

        // Act
        User foundUser = userRepository.findByEmailAndPassword("email@mail.com", "password");

        // Assert
        assertNotNull(foundUser.getId());
        assertEquals("name", foundUser.getName());
    }

}
