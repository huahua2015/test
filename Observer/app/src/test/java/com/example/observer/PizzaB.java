package com.example.observer;

public class PizzaB extends Decorator {
    private Pizza pizza;

    public PizzaB(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getprice() {
        return pizza.getprice()+20;
    }

    @Override
    public String getName() {
        return  pizza.getName()+"+牛肉";
    }
}
