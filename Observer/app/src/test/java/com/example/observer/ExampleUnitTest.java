package com.example.observer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        Pizza pizza=new BasePizza();
        PizzaA p=new PizzaA(new PizzaB(new PizzaC(pizza)));
        p.show();
    }

    @Test
    public void test_observer(){
        WeixinUser weixinUser1=new WeixinUser("杨影风");
        WeixinUser weixinUser2=new WeixinUser("月儿");
        WeixinUser weixinUser3=new WeixinUser("紫霞");
        SubscriptionSubject subscriptionSubject=new SubscriptionSubject();
        subscriptionSubject.attach(weixinUser1);
        subscriptionSubject.attach(weixinUser2);
        subscriptionSubject.attach(weixinUser3);
        subscriptionSubject.notify("刘望舒的专栏更新了");
    }


}