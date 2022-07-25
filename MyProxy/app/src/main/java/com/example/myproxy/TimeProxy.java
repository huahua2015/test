package com.example.myproxy;

import android.util.Log;

public class TimeProxy implements ISubject{
    private static final String TAG = "TimeProxy";
    private ISubject iSubject;

    public TimeProxy(ISubject iSubject) {
        this.iSubject = iSubject;
    }
    @Override
    public void doSomething() {
        Log.e(TAG, "开始执行时间...");
        iSubject.doSomething();
        Log.e(TAG, "结束执行时间...");
    }
}
