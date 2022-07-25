package com.example.mybuider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Builder macBookBuilder=new MacBookBuilder();
      //  Director director = new Director(macBookBuilder);
        // 使用Director构造
       // director.construct("英特尔主板","retina");
        System.out.println("1====="+macBookBuilder.create().toString());
        // 通常我们都会通过Builder的链式调用省略Director类，比如如下所示
        System.out.println("2====="+macBookBuilder.buildBoard("英特尔主板")
                .buildDisplay("retina")
                .buildOS()
                .create().toString());

        Person.Builder builder=new Person.Builder();
        Person person0=builder.name("张三").builder();
        Person person1=builder.name("张三").sex("男").builder();
        Person person2=builder.name("张三").sex("男").age(20).builder();
    }
}