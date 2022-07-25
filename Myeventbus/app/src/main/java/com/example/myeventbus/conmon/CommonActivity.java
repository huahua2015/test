package com.example.myeventbus.conmon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myeventbus.R;

import org.greenrobot.eventbus.EventBus;

public class CommonActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        btn = findViewById(R.id.btn_send_message);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setAge(15);
                messageEvent.setName("小明");
                EventBus.getDefault().post(messageEvent);

                finish();
            }
        });
    }
}