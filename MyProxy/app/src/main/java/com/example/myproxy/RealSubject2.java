package com.example.myproxy;

import android.util.Log;

public class RealSubject2 implements ISubject {
    private static final String TAG = RealSubject2.class.getName();

    @Override
    public void doSomething() {
        Log.e(TAG, "doSomething: 执行中");
    }
}
