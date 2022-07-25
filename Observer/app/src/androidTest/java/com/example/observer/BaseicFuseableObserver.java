package com.example.observer;

public abstract class BaseicFuseableObserver<T,R> implements Observer<T>{
 //观察者
    protected  final Observer<R> actual;

    protected BaseicFuseableObserver(Observer<R> actual) {
        this.actual = actual;
    }
    @Override
    public void onSubscribe() {
        actual.onSubscribe();
    }



    @Override
    public void onError(Throwable e) {
        actual.onError(e);
    }

    @Override
    public void onComplete() {
        actual.onComplete();
    }
}
