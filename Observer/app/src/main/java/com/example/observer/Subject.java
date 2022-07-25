package com.example.observer;

public interface Subject<T> {
    void registerObserver(Observers<T> observer);

    void removeObserver(Observers<T> observer);


    void notifyObservers();

}
