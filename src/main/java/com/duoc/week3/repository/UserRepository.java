package com.duoc.week3.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.duoc.week3.model.Pet;
import com.duoc.week3.model.User;
import com.duoc.week3.model.User.Role;

// Repository layer for User
@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    // Return all users
    public List<User> getAllUsers() {
        return users;
    }

    // Return an user by id
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    public UserRepository() {
        // Create pet owners in memory with some pets
        users.add(new User(1, "Daniel Santander", "da.santandera@duocuc.cl", "+56912345678", Role.PET_OWNER,
                new GregorianCalendar(2000, 4, 20).getTime(), Date.from(new Date().toInstant()), new ArrayList<Pet>() {
                    {
                        add(new Pet(1, "Firulais", "Perro", "Labrador", "Cafe", "5", "10", "Es un perro muy jugueton"));
                    }
                }));
        users.add(new User(2, "Pedro Canales", "pe.canales@duocuc.cl", "+56943125678", Role.PET_OWNER,
                new GregorianCalendar(1990, 8, 9).getTime(), Date.from(new Date().toInstant()), new ArrayList<Pet>() {
                    {
                        add(new Pet(2, "Blanquita", "Gato", "Siames", "Blanco", "3", "8.40", "Gatita blanquita"));
                    }
                }));

        // Create pet friendly drivers in memory
        users.add(new User(3, "Luis Coronel", "lu.coro@gmail.com", "+56987654321", Role.PET_FRIENDLY_DRIVER,
                new GregorianCalendar(1970, 3, 12).getTime(), Date.from(new Date().toInstant()), new ArrayList<Pet>()));
        users.add(new User(4, "Ernesto Belloni", "er.belloni@gmail.com", "+56987651234", Role.PET_FRIENDLY_DRIVER,
                new GregorianCalendar(1961, 10, 11).getTime(), Date.from(new Date().toInstant()),
                new ArrayList<Pet>()));
    }
}
