package com.example.myproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private static final String TAG = "DynamicProxy";
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理类
     * @param method 被代理的方法
     * @param args   被代理方法的参数
     * @return 返回代理对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e(target.getClass().getName(), "开始日志。。。");
        method.invoke(target);
        Log.e(target.getClass().getName(), "结束日志。。。");
        return null;
    }
}