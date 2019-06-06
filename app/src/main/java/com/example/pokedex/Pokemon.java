package com.example.pokedex;

public class Pokemon {
    private String name;
    private int base_experience, height, weight;

    public Pokemon(String name, int base_experience, int height, int weight) {
        this.name = name;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

}
