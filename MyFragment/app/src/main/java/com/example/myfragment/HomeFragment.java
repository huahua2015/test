package com.example.myfragment;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    private final String TAG = "ZZF";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"------------onCreateView-------------");
        return inflater.inflate(R.layout.fragment_home,container,false);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"------------onAttach-------------");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG,"------------onViewCreated-------------");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"------------onStart-------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"------------onResume-------------");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"------------onPause-------------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"------------onDestroy-------------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"------------onDestroyView-------------");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"------------onStop-------------");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"------------onCreate-------------");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"------------onActivityCreated-------------");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG,"------------onHiddenChanged-------------"+hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG,"------------setUserVisibleHint-------------");
    }
}
