package com.duoc.week3.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.week3.model.Pet;

// Repository layer for Pet
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    // Retrieve all pets
    List<Pet> findAll();

    Pet findById(int id);
}
