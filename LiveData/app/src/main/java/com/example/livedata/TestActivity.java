package com.example.livedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private TextView textView;
    private TestModel mTestModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.tv);
        initData();
    }

    private void initData() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        };

        mTestModel = new TestModel();
        mTestModel.getStatus().observe(this,observer);
        mTestModel.getStatus().setValue("oncreate");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTestModel.getStatus().setValue("onStop");
    }
    @Override
    protected void onStart() {
        super.onStart();
        mTestModel.getStatus().setValue("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestModel.getStatus().setValue("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTestModel.getStatus().setValue("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTestModel.getStatus().setValue("onDestroy");
    }
}