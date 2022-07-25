package com.example.observer;

public class PizzaA extends Decorator {
    private Pizza pizza;

    public PizzaA(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getprice() {
        return pizza.getprice()+10;
    }

    @Override
    public String getName() {
        return  pizza.getName()+"+菠萝";
    }
}
