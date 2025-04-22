package com.duoc.week3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pet_id")
    @SequenceGenerator(name = "sq_pet_id", sequenceName = "sq_pet_id", allocationSize = 1)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotNull(message = "Type cannot be null")
    @Size(min = 2, max = 30, message = "Type must be between 2 and 30 characters")
    private String type;

    @NotNull(message = "Breed cannot be null")
    @Size(min = 2, max = 30, message = "Breed must be between 2 and 30 characters")
    private String breed;

    @NotNull(message = "Color cannot be null")
    @Size(min = 2, max = 30, message = "Color must be between 2 and 30 characters")
    private String color;

    @NotNull(message = "Age cannot be null")
    private int age;

    @NotNull(message = "Weight cannot be null")
    private double weight;

    @NotNull(message = "Description cannot be null")
    @Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User owner;
}
