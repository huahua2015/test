package com.example.observer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements Observers<Integer> {//这里也是观察者
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownLoadSubject.getInstance().registerObserver(this);
        seekBar = findViewById(R.id.seekBar);

        WeixinUser weixinUser1=new WeixinUser("杨影风");
        WeixinUser weixinUser2=new WeixinUser("月儿");
        WeixinUser weixinUser3=new WeixinUser("紫霞");
        SubscriptionSubject subscriptionSubject=new SubscriptionSubject();
        subscriptionSubject.attach(weixinUser1);
        subscriptionSubject.attach(weixinUser2);
        subscriptionSubject.attach(weixinUser3);
        subscriptionSubject.notify("刘望舒的专栏更新了");
    }

    @Override
    public void onUpdate(Integer integer) {
        seekBar.setProgress(integer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownLoadSubject.getInstance().removeObserver(this);//这里记得删除当前观察者，不然会报异常
    }

    public void startDetail(View view) {
        startActivity(new Intent(getApplicationContext(), ObserverActivityDetail.class));
    }
}