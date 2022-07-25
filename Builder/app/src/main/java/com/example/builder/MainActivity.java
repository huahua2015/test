package com.example.builder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public Student getStudent(){
        return new Student.StudentBuilder(1,"小明")//必填属性在构造方法中赋值
                .setAge(1)//设置可选属性
                .setGender(1)//设置可选属性
                .build();//对象构建完毕的标识，返回Student对象
    }
}