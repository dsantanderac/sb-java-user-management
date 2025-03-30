package com.duoc.week3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.week3.model.Pet;
import com.duoc.week3.repository.PetRepository;

// Service layer to manage business logic
@Service
public class PetServiceImpl implements PetService {
    // Dependency Injection
    private final PetRepository petRepository = new PetRepository();

    // Override get all pets method with the business logic
    @Override
    public List<Pet> getAllPets() {
        return petRepository.getAllPets();
    }

    // Override get pet by id method with the business logic
    @Override
    public Pet getPetById(int id) {
        return petRepository.getPetById(id);
    }

}
