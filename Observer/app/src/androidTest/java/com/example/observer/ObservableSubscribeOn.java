package com.example.observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSubscribeOn<T> extends AbstractObservableWithUpstream<T,T>{

    private static ExecutorService executorService= Executors.newCachedThreadPool();

    public ObservableSubscribeOn(ObserverSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        final SubsrcibeObserver<T> parent=new SubsrcibeObserver<>(observer);
        observer.onSubscribe();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                source.subscribe(parent);
            }
        });


    }

    static final class SubsrcibeObserver<T> implements Observer<T>{
        final Observer<T> actual;

        SubsrcibeObserver(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            actual.onNext(t);
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
}
