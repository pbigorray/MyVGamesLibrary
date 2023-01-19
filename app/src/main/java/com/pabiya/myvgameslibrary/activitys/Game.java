package com.pabiya.myvgameslibrary.activitys;

public class Game {
    private int id,alquilado;
    private float price;
    private String name,gender;

    public Game(int id,String name,String gender, float price,int alquilado) {
        this.id = id;
        this.alquilado = alquilado;
        this.price = price;
        this.name = name;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAlquilado() {
        return alquilado;
    }

    public float getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }
}
