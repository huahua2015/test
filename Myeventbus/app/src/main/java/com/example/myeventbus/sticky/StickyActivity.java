package com.example.myeventbus.sticky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myeventbus.R;
import com.example.myeventbus.conmon.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class StickyActivity extends AppCompatActivity {
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        button = findViewById(R.id.btn_sticky);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setName("hehe");
                messageEvent.setAge(20);
                EventBus.getDefault().postSticky(messageEvent);

                finish();
            }
        });
    }
}