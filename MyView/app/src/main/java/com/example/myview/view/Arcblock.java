package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Arcblock extends View {
    private int width;
    public Arcblock(Context context) {
        super(context,null);
    }

    public Arcblock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Arcblock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //定义画笔
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(Color.BLUE);
        //设置画笔类型
        paint.setStyle(Paint.Style.FILL);
       canvas.drawRect(0, 0, width,400, paint);
        Path mPath = new Path();
        mPath.moveTo(0,400);
        mPath.rQuadTo(width/2,150,width,0);
        mPath.close();
        canvas.drawPath(mPath,paint);
    }
}
