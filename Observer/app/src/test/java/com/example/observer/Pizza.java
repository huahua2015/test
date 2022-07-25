package com.example.observer;

public abstract class Pizza {
   protected String name;
    public String getName(){
        return this.name;
    }
    public abstract double getprice();
}
