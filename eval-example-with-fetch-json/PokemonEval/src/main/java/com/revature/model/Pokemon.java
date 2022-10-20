package com.revature.model;

import java.util.Objects;

public class Pokemon {

    private int numId;
    private String name;
    private int level;
    private String image;

    public Pokemon(){}

    public Pokemon(int numId, String name, int level, String image){
        this.numId = numId;
        this.name = name;
        this.level = level;
        this.image = image;
    }

    public int getNumId() {
        return numId;
    }

    public void setNumId(int numId) {
        this.numId = numId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "numId=" + numId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return numId == pokemon.numId && level == pokemon.level && Objects.equals(name, pokemon.name) && Objects.equals(image, pokemon.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numId, name, level, image);
    }
}
