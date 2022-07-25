package com.example.myproxy;

import android.util.Log;

public class RealSubject implements ISubject {
    private static final String TAG = "RealSubject";

    @Override
    public void doSomething() {
        Log.e(TAG, "doSomething: 执行中");
    }
}
