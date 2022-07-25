package com.example.aildservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public MyService()
    {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    class MyBinder extends IMyAidlInterface.Stub
    {

        @Override
        public String getname() throws RemoteException {
            return "test";
        }
    }
}
