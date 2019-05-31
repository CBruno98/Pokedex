package com.example.pokedex;

public class Pokemon {
    private String name;
    private int exp, altura, peso;
    private int imgID;

    public Pokemon(String name, int imgID) {
        this.name = name;
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public int getImgID() {
        return imgID;
    }
}
