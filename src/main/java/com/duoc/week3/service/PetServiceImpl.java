package com.duoc.week3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.week3.model.Pet;
import com.duoc.week3.repository.PetRepository;

import lombok.RequiredArgsConstructor;

// Service layer to manage business logic
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    // Override get all pets method with the business logic
    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    // Override get pet by id method with the business logic
    @Override
    public Pet getById(int id) {
        return petRepository.findById(id);
    }

}
