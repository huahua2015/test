package com.example.observer;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionSubject implements Subject2{
    private List<Observer<T>> weixinuserlist=new ArrayList<>();
    @Override
    public void attach(Observer<T> observer) {
        weixinuserlist.add(observer);
    }

    @Override
    public void dettach(Observer<T> observer) {
        weixinuserlist.remove(observer);
    }

    @Override
    public void notify(String message) {
        for(Observer<T> observer:weixinuserlist){
            observer.notify(message);
        }
    }
}
