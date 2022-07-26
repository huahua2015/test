package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Dashboard extends View {

    private static final int ANGLE = 120;
    private static final float RADIUS = 150;
    private static final float LENGTH =100;

    Paint paint = new Paint();
    PathEffect pathEffect;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        /**
         * 使用PathDashPathEffect画圆弧上的刻度
         */
        Path dashPath = new Path();
        dashPath.addRect(0, 0, 2,10, Path.Direction.CCW);

        //计算圆弧的长度，为了计算出每个刻度之间的间隔
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);

        pathEffect = new PathDashPathEffect(dashPath, (pathMeasure.getLength() - 2) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆弧
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                ANGLE / 2 + 90, 360 - ANGLE, false, paint);

        // 画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() / 2,
                paint);
        //画一个圆弧，然后将 PathDashPathEffect 设置到 paint 上，画出圆弧上面的刻度
        paint.setPathEffect(pathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);


    }

    int getAngleFromMark(int mark) {
        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);
    }

}
