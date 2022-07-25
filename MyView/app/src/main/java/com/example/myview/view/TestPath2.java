package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestPath2 extends View {
    public TestPath2(Context context) {
        super(context);
    }

    public TestPath2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestPath2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        /*mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        canvas.translate((getWidth() / 2), (getHeight()/ 2));
        Path path = new Path();
        // 叠加两个不同半径圆形组成自相交图形用来展示不同Direction下的非零环绕规则
        path.addCircle(-250f, 0f, 100f, Path.Direction.CW);
        path.addCircle(-250f, 0f, 200f, Path.Direction.CCW);

        path.addCircle(250f, 0f, 100f, Path.Direction.CCW);
        path.addCircle(250f, 0f, 200f, Path.Direction.CCW);

        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, mPaint);*/
        Path path=new Path();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4f);
        paint.setColor(Color.RED);
        Path path1 = new Path();
        Path path2 = new Path();
        path1.addCircle(0f, 0f, 100f, Path.Direction.CCW);
        path2.addCircle(100f, 100f, 50f, Path.Direction.CW);
        path.addPath(path1);
        path.addPath(path2);
        canvas.drawPath(path1, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawPath(path2, paint);
        super.onDraw(canvas);
    }
}
