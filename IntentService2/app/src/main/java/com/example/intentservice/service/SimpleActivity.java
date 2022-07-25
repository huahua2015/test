package com.example.intentservice.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.intentservice.R;

public class SimpleActivity extends AppCompatActivity {
    private Button btn_start,btn_destory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        btn_start = findViewById(R.id.btn_start);

        btn_destory = findViewById(R.id.btn_destory);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SimpleActivity.this,SimpleService.class);
                startService(intent);

        }
        });
        btn_destory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SimpleActivity.this,SimpleService.class);
                stopService(intent);
            }
        });
        }
        }