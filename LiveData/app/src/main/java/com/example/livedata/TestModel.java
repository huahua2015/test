package com.example.livedata;

import androidx.lifecycle.MutableLiveData;

public class TestModel {
    private MutableLiveData<String> status;

    public MutableLiveData<String> getStatus() {
        if (status == null){
            status = new MutableLiveData<>();
        }
        return status;
    }
}
