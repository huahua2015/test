package com.example.bitmapcache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        BitmapAdapter bitmapAdapter = new BitmapAdapter(this);
        rv.setAdapter(bitmapAdapter);
        ImageCache.getInstance().init(this, this.getExternalCacheDir() +"/bitmap");
        Log.d(TAG,"this.getExternalCacheDir()="+this.getExternalCacheDir());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageCache.getInstance().setExit(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ImageCache.getInstance().interruptThread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageCache.getInstance().interruptThread();
    }
}