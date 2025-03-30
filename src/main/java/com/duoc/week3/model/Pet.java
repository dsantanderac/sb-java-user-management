package com.duoc.week3.model;

public class Pet {
    private int id;
    private String name;
    private String type;
    private String breed;
    private String color;
    private int age;
    private double weight;
    private String description;

    public Pet(int id, String name, String type, String breed, String color, String age, String weight,
            String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.color = color;
        this.age = Integer.parseInt(age);
        this.weight = Double.parseDouble(weight);
        this.description = description;
    }

    public int getPetId() {
        return this.id;
    }

    public String getPetName() {
        return this.name;
    }

    public String getPetType() {
        return this.type;
    }

    public String getPetBreed() {
        return this.breed;
    }

    public String getPetColor() {
        return this.color;
    }

    public int getPetAge() {
        return this.age;
    }

    public double getPetWeight() {
        return this.weight;
    }

    public String getPetDescription() {
        return this.description;
    }
}
