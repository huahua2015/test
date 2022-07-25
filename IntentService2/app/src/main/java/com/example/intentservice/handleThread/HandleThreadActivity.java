package com.example.intentservice.handleThread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.intentservice.R;

public class HandleThreadActivity extends AppCompatActivity {
    private TextView mTextView;
    private HandlerThread handlerThread;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_4);
        mTextView = findViewById(R.id.tv);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerThread =new HandlerThread("handlerThread");
                handlerThread.start();
                handler=new Handler(handlerThread.getLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == 1){

                            String str = (String) msg.obj;

                        }

                        Log.d("Kathy","Received Message = " + msg.what + "   CurrentThread = " + Thread

                                .currentThread().getName());
                    }
                };
                Message message = Message.obtain();

                message.obj = "this is message";

                message.what = 1;

                handler.sendMessage(message);
            }
        });
        new Thread(new Runnable() {

            @Override

            public void run() {

                handler.sendEmptyMessage(2);

            }

        }).start();
    }

}