package com.duoc.week3.service;

import java.util.List;

import com.duoc.week3.model.Pet;

public interface PetService {
    List<Pet> getAllPets();

    Pet getPetById(int id);
}
