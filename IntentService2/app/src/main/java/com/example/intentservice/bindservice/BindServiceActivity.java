package com.example.intentservice.bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.intentservice.R;
import com.example.intentservice.service.SimpleService;

public class BindServiceActivity extends AppCompatActivity {
    private Button btn_bind,btn_unbind;
    private SimpleService.Mybinder mybinder;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mybinder=(SimpleService.Mybinder)iBinder;
            String con=mybinder.getInfo();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mybinder = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        btn_bind = findViewById(R.id.btn_bind);

        btn_unbind = findViewById(R.id.btn_unbind);

        btn_bind.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(BindServiceActivity.this,SimpleService.class);

                // 绑定服务

                bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);



            }

        });

        btn_unbind.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                unbindService(serviceConnection);

            }

        });
    }
}