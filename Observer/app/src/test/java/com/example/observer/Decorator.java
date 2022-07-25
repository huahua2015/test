package com.example.observer;

public class Decorator extends Pizza {
    @Override
    public double getprice() {
        return this.getprice();
    }
    public void show(){
        System.out.println(getName()+" "+getprice());
    }
}
