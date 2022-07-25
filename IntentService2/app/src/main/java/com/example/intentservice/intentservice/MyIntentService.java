package com.example.intentservice.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {
    private boolean isRunning = true;
    private LocalBroadcastManager mLocalBroadcastManager;
    private int count;
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {

        super("MyIntentService");

        Log.e("zzf","MyIntentService");

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager=LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("zzf","onHandleIntent");

        try {

            Thread.sleep(1000);

            count = 0;

            while (isRunning){

                count += 5;

                if (count >= 100){

                    isRunning = false;

                }

                Thread.sleep(50);

                sendThreadStatus("线程运行中...", count);

            }

        }catch (Exception e){

            e.printStackTrace();

        }
    }
    private void sendThreadStatus(String status, int count) {

        Intent intent = new Intent(IntentServiceActivity.ACTION_TYPE_THREAD);

        intent.putExtra("status",status);

        intent.putExtra("count",count);

        mLocalBroadcastManager.sendBroadcast(intent);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
