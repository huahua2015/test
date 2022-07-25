package com.example.observer;

public class BasePizza extends Pizza{
    @Override
    public String getName() {
        this.name="Pizza";
        return this.name;
    }

    @Override
    public double getprice() {
        return 50;
    }
}
