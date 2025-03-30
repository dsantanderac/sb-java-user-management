package com.duoc.week3.model;

import java.util.Date;
import java.util.List;

public class User {
    public enum Role {
        PET_OWNER,
        PET_FRIENDLY_DRIVER
    }

    private int id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Date birthDate;
    private Date created_at;
    private List<Pet> pets;

    public User(int id, String name, String email, String phone, Role role, Date birthDate,
            Date created_at, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.birthDate = birthDate;
        this.created_at = created_at;
        this.pets = pets;
    }

    public int getUserId() {
        return this.id;
    }

    public String getUserName() {
        return this.name;
    }

    public String getUserEmail() {
        return this.email;
    }

    public String getUserPhone() {
        return this.phone;
    }

    public Role getUserRole() {
        return this.role;
    }

    public Date getUserBirthDate() {
        return this.birthDate;
    }

    public Date getUserCreatedAt() {
        return this.created_at;
    }

    public List<Pet> getUserPets() {
        return this.pets;
    }
}
