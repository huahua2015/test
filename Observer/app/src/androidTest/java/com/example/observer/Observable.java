package com.example.observer;
//具体被观察者
public abstract class Observable<T> implements ObserverSource<T> {
    @Override
    public void subscribe(Observer<T> observer) {
    //我们把这个订阅的功能让observble的子类去完成
        subscribeActual(observer);
    }
    protected abstract void subscribeActual(Observer<T> observer);
    //创造被观察者的实列
    public  static<T>  Observable create(ObservableOnSubscribe<T> source){
        return new ObservableCreate(source);
    }
    //创建被观察者
    public <U> Observable<U> map(Function<T,U> function){
        return new ObservableMap<>(this,function);
    }
    //被观察者的线程切换
   public final Observable<T> subsribeOn(){
        return new ObservableSubscribeOn<>(this);
    }
    //观察者的线程切换
    public final Observable<T> observerOn(){
        return new ObservableObserveOn<>(this);
    }
}
