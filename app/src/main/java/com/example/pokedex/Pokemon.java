package com.example.pokedex;

public class Pokemon {
    private String name;
    private int base_experience, height, weight;
    private Sprites sprites;

    public Pokemon(String name, int base_experience, int height, int weight, Sprites sprites) {
        this.name = name;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
        this.sprites = sprites;
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

    public Object getSprites() {
        return sprites;
    }
}
