package com.example.intentservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimpleService extends Service {
    private static final String TAG = "SimpleService";
    private Mybinder mybinder=new Mybinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"-----------onBind--------------");

        //不能再这个方法中做延时操作，非要做延时操作，使用IntentService。
        return mybinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class Mybinder extends Binder{
        public String getInfo(){

            return "调用了服务中的方法";

        }
    }
}
