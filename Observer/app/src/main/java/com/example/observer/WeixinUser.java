package com.example.observer;

public class WeixinUser implements Observer<T> {
    private String name;
    public WeixinUser(String name){
        this.name=name;
    }

    @Override
    public void notify(String message) {
        System.out.print(name+"_"+message);
    }
}
