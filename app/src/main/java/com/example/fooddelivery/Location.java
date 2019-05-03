package com.example.fooddelivery;

public class Location {
    private int id;
    private String name;
    private double price;

    public  Location(){

    }
    public Location (int id, String name, int price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString(){
        return this.id+""+this.name+""+this.price;
    }
}
