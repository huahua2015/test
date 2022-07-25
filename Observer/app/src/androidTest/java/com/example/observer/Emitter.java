package com.example.observer;

public interface Emitter <T>{
    //发送消息
    void onNext(T t);
    //发送异常
    void onError(Throwable throwable);
    //发送完成的信号
    void onComplete();
}
