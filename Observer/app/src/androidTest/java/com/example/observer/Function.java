package com.example.observer;
/*
实现转换与传递的功能
 */
public interface Function<T,R> {
    //对输入值运用一些计算，得到一个结果，再传到下游
    R apply(T t);
}
