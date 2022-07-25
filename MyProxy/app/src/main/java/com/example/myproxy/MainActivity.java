package com.example.myproxy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RunTimeInjector.injectView(this);
       // tv.setText("kkkkk");
        RealSubject realSubject = new RealSubject();
        //先记录时间  在打印日志
        ISubject logProxy = new LogProxy(realSubject);
        TimeProxy timeProxy = new TimeProxy(logProxy);
        timeProxy.doSomething();
        Log.e("test", "============================================ ");
        //先打印日志  再记录时间
        TimeProxy timeProxy1 = new TimeProxy(realSubject);
        ISubject logProxy1 = new LogProxy(timeProxy1);
        logProxy1.doSomething();
        Log.e("test", "===================RealSubject========================= ");
      /*  RealSubject realSubject = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        ISubject proxyInstance = (ISubject) Proxy.newProxyInstance(classLoader, realSubject.getClass().getInterfaces(), dynamicProxy);
        proxyInstance.doSomething();*/

        Log.e("test", "===================RealSubject2========================= ");
        RealSubject2 realSubject2 = new RealSubject2();
        DynamicProxy dynamicProxy2 = new DynamicProxy(realSubject2);
        ClassLoader classLoader2 = realSubject2.getClass().getClassLoader();
        ISubject proxyInstance2 = (ISubject) Proxy.newProxyInstance(classLoader2, realSubject2.getClass().getInterfaces(), dynamicProxy2);
        proxyInstance2.doSomething();
    }
}