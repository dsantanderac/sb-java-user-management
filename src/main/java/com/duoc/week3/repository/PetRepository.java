package com.duoc.week3.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.duoc.week3.model.Pet;

// Repository layer for Pet
@Repository
public class PetRepository {
    private final List<Pet> pets = new ArrayList<Pet>();

    // Retrieve all pets
    public List<Pet> getAllPets() {
        return pets;
    }

    // Retrieve pet by id
    public Pet getPetById(int id) {
        for (Pet pet : pets) {
            if (pet.getPetId() == id) {
                return pet;
            }
        }
        return null;
    }

    public PetRepository() {
        // Create some pets in-memory
        pets.add(new Pet(1, "Firulais", "Perro", "Labrador", "Cafe", "5", "10", "Es un perro muy jugueton"));
        pets.add(new Pet(2, "Blanquita", "Gato", "Siames", "Blanco", "3", "8.40", "Gatita blanquita"));
        pets.add(new Pet(3, "Theo", "Perro", "Golden Retriever", "Dorado", "8", "25", "Es gigante!"));
    }
}
