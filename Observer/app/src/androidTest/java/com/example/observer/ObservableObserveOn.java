package com.example.observer;

import android.os.Looper;

public class ObservableObserveOn<T> extends AbstractObservableWithUpstream<T,T>{
    public ObservableObserveOn(ObserverSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(final Observer<T> observer) {
        final ObserverOnObserver<T> parent=new ObserverOnObserver<>(observer);
        source.subscribe(parent);
    }
    static final class ObserverOnObserver<T> implements Observer<T>{
        final Observer<T> actual;
        private android.os.Handler handler;

       public ObserverOnObserver(Observer<T> actual) {
            this.actual = actual;
           handler = new android.os.Handler(Looper.getMainLooper());
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(final T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });
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
