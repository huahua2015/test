package com.example.observer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class ObserverActivityDetail extends AppCompatActivity  implements Observers<Integer> {
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_detail);
        DownLoadSubject.getInstance().registerObserver(this);
        seekBar = findViewById(R.id.seekBar);
    }

    @Override
    public void onUpdate(Integer integer) {
        seekBar.setProgress(integer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownLoadSubject.getInstance().removeObserver(this);
    }
}