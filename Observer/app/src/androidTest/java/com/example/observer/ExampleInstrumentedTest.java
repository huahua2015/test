package com.example.observer;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {


        // Context of the app under test.
     ///   Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
      //  assertEquals("com.example.observer", appContext.getPackageName());
  Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(Emitter<String> emitter) {
          emitter.onNext("大家好");
      }
  }).map(new Function<String,String>(){
      @Override
      public String apply(String s) {
          System.out.println("收到消息1"+s);
          return s;
      }
  }).map(new Function<String,String>(){
      @Override
      public String apply(String s) {
          System.out.println("收到消息2"+s);
          return s;
      }
  }).subsribeOn().observerOn().subscribe(new Observer() {
      @Override
      public void onSubscribe() {

      }

      @Override
      public void onNext(Object o) {
          System.out.println("收到消息3"+(String)o);

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
  });
    }
}