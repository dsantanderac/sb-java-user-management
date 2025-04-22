package com.duoc.week3.service;

import java.util.List;

import com.duoc.week3.model.Pet;

public interface PetService {
    List<Pet> getAll();

    Pet getById(int id);
}
