package com.example.myproxy;

import android.util.Log;

public class LogProxy implements ISubject{
    private static final String TAG = "LogProxy";
    private ISubject iSubject;

    public LogProxy(ISubject iSubject) {
        this.iSubject = iSubject;
    }
    @Override
    public void doSomething() {
        Log.e(TAG, "日志开始。。。");
        iSubject.doSomething();
        Log.e(TAG, "日志结束。。。");
    }
}
