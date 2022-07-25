package com.example.myview;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myview.view.CustomView3;
import com.example.myview.view.KingoitFlowLayout;
import com.example.myview.view.MikyouCommonDialog;
import com.example.myview.view.MyTapeView;
import com.example.myview.view.Mytextview;
import com.example.myview.view.TapeView;
import com.example.myview.view.UserMainView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class MainActivity extends Activity implements KingoitFlowLayout.ItemClickListener, Mytextview.ItemClickListener,TapeView.OnValueChangeListener,MyTapeView.OnValueChangeListener {
    private KingoitFlowLayout flowLayout;
    private List<String> list = new ArrayList<>();
    public static Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
    public Handler mHandler;
    private CustomView3 customView3;
    private UserMainView userMainView;
    private static final String TAG = "MainActivity";

    private TextView tvWeight;
    private TextView tvHeight;
    private MyTapeView tapeWeight;
    private TapeView tapeHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.kingoit_flow_layout);
        initData();
        initView();
        customView3=(CustomView3)findViewById(R.id.custom_view);
       // customView3.setRadius(20);
        customView3.refreshView();
      //  dialog1();
        userMainView=(UserMainView)findViewById(R.id.usermainview);
        userMainView.setClickListener(new UserMainView.UserMainTouchListener() {
            @Override
            public void ClinckOne() {
                Log.d("MainActivity","one");
            }

            @Override
            public void ClickTwo() {
                Log.d("MainActivity","two");

            }

            @Override
            public void ClickThree() {
                Log.d("MainActivity","three");

            }

            @Override
            public void ClickFour() {
                Log.d("MainActivity","four");

            }

            @Override
            public void ClickFive() {
                Log.d("MainActivity","five");

            }
        });
        initViews();

        tapeWeight.setOnValueChangeListener(this);
        tapeHeight.setOnValueChangeListener(new TapeView.OnValueChangeListener() {
            @Override
            public void onChange(float value) {
                tvHeight.setText(value + " " + getString(R.string.cm));
            }
        });

       // tapeView.setValue(65, 40.7f, 200, 0.1f, 10);
       // tvWeight.setText(tapeWeight.getValue() + " " + getString(R.string.kg));
        tvHeight.setText(tapeHeight.getValue() + " " + getString(R.string.cm));
    }
    private void initViews() {
        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        tapeWeight = (MyTapeView) findViewById(R.id.tapeWeight);
        tapeHeight = findViewById(R.id.tapeHeight);
    }

    @Override
    public void onChange(float value) {
        tvWeight.setText(value + " " + getString(R.string.kg));
    }
    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("战争女神");
            list.add("蒙多");
            list.add("德玛西亚皇子");
            list.add("殇之木乃伊");
            list.add("狂战士");
            list.add("布里茨克拉克");
            list.add("冰晶凤凰 艾尼维亚");
            list.add("德邦总管");
            list.add("野兽之灵 乌迪尔 （德鲁伊）");
            list.add("赛恩");
            list.add("诡术妖姬");
            list.add("永恒梦魇");
        }
    }

    private void initView() {
        flowLayout.showTag(list, this);
    }


    @Override
    public void onClick(String currentSelectedkeywords, List<String> allSelectedKeywords) {
        Toast.makeText(this, currentSelectedkeywords, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick() {

    }
    public void dialog1(){//弹出第一个对话框
        new MikyouCommonDialog(this,"普通文本提示信息对话框","测试一","确定","取消")
                .setOnDiaLogListener(new MikyouCommonDialog.OnDialogListener() {


                    @Override
                    public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {

                    }

                    @Override
                    public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {

                    }
                }).showDialog();
    }
    public void dialog2(View view){//弹出第二个对话框
        new MikyouCommonDialog(this,"密码错误","测试二","重新输入","取消")
                .setOnDiaLogListener(new MikyouCommonDialog.OnDialogListener() {

                    @Override
                    public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {

                    }

                    @Override
                    public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {

                    }
                }).showDialog();
    }
   /* public void dialog3(View view){//弹出第三个对话框
        new MikyouCommonDialog(this,R.layout.input_text_dialog,"测试三带有自定义View的布局","确定","取消")
                .setOnDiaLogListener(new MikyouCommonDialog.OnDialogListener() {
                    @Override
                    public void dialogPositiveListener(View customView, DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void dialogNegativeListener(View customView, DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                }).showDialog();
    }*/

}