package com.example.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
