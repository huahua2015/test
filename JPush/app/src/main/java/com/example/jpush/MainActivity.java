package com.example.jpush;

import androidx.appcompat.app.AppCompatActivity;
import cn.jpush.android.api.JPushInterface;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.onResume(this);
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                JPushInterface.setAlias(MainActivity.this,200,"llh");
            }
        },8000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}