package com.example.jetpack.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jetpack.R;

public class LifecycleActivity extends AppCompatActivity {
    private LifecycleObserverImpl lifecycleObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        lifecycleObserver = new LifecycleObserverImpl();
        getLifecycle().addObserver(lifecycleObserver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(lifecycleObserver);
    }
}