package com.example.observer;
/*
抽象被观察者
 */
public interface ObserverSource<T> {
    //notify()  将给定的Observer订阅
    void subscribe(Observer<T> observer);
}
