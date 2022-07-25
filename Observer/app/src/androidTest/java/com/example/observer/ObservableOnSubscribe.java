package com.example.observer;

public interface ObservableOnSubscribe<T> {
    //为每个订阅的观察者提供一个能进行消息发送的功能
    void subscribe(Emitter<T> emitter) ;

}
