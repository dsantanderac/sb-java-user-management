package com.duoc.week3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_user_id")
    @SequenceGenerator(name = "sq_user_id", sequenceName = "sq_user_id", allocationSize = 1, initialValue = 1)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    private String password;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 characters")
    private String phone;

    @NotNull(message = "Birth Date cannot be null")
    private LocalDate birthDate;
    private LocalDateTime created_at = LocalDateTime.now();
    private LocalDateTime updated_at = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();
}
