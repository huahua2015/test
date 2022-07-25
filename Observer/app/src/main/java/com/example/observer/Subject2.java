package com.example.observer;

public interface Subject2 {
    /*
    增加订阅者
     */
    public void attach(Observer<T> observer);
    /*
    删除订阅者
     */
    public void dettach(Observer<T> observer);
    /*
    通知订阅者更新消息
     */
    public void notify(String message);
}
