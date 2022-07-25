package com.example.observer;

public abstract class AbstractObservableWithUpstream<T,U> extends Observable<U> {
    protected final ObserverSource<T> source;
    public AbstractObservableWithUpstream(ObserverSource<T> source) {
        this.source = source;
    }
}
