package com.example.observer;

public class PizzaC  extends Decorator {
    private Pizza pizza;

    public PizzaC(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getprice() {
        return pizza.getprice()+15;
    }

    @Override
    public String getName() {
        return  pizza.getName()+"+洋葱";
    }
}