package com.example.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.myview.R;

import androidx.annotation.Nullable;

public class CustomView3 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path;
    private int color = Color.GREEN;
    private final int LEFT_TOP = 0x1;
    private final int LEFT_BOTTOM = 0x2;
    private final int RIGHT_TOP = 0x4;
    private final int RIGHT_BOTTOM = 0x8;
    private boolean drawLeftTop;
    private boolean drawLeftBottom;
    private boolean drawRightTop;
    private boolean drawRightBottom;
    private float radius;

    public CustomView3(Context context) {
        super(context);
        initDraw();
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        int position = typedArray.getInt(R.styleable.CustomView_round_position, 0);
      //  radius = typedArray.getDimension(R.styleable.CustomView_round_radius, 0);
        radius=100;
        drawLeftTop = (position & LEFT_TOP) == LEFT_TOP;
        drawLeftBottom = (position & LEFT_BOTTOM) == LEFT_BOTTOM;
        drawRightTop = (position & RIGHT_TOP) == RIGHT_TOP;
        drawRightBottom = (position & RIGHT_BOTTOM) == RIGHT_BOTTOM;
        typedArray.recycle();
        initDraw();
    }

    public CustomView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw() {
        path = new Path();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) 5);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();//这里很重要，如果不写这一行，则每次重绘view后先前绘制的还会存在
        path.moveTo(radius, 0);
        if (drawRightTop) {
            path.lineTo(getWidth() - radius, 0);
//      path.cubicTo(radius + getWidth() / 3, 0, radius + getWidth() / 3 * 2, 0, getWidth() - radius, 0);
            path.cubicTo(getWidth() - radius / 2, 0, getWidth(), radius / 2, getWidth(), radius);
        } else {
            path.lineTo(getWidth(), 0);
//      path.cubicTo(radius + getWidth() / 3, 0, radius + getWidth() / 3 * 2, 0, getWidth(), 0);
        }
        path.lineTo(getWidth(), getHeight() - radius);
//    path.cubicTo(getWidth(), radius + getHeight() / 3, getWidth(), radius + getHeight() / 3 * 2, getWidth(), getHeight() - radius);
        if (drawRightBottom) {
            path.cubicTo(getWidth(), getHeight() - radius / 2, getWidth() - radius / 2, getHeight(), getWidth() - radius, getHeight());
        } else {
            path.lineTo(getWidth(), getHeight());
        }
        path.lineTo(radius, getHeight());
        if (drawLeftBottom) {
            path.cubicTo(radius / 2, getHeight(), 0, getHeight() - radius / 2, 0, getHeight() - radius);
        } else {
            path.lineTo(0, getHeight());
        }
        path.lineTo(0, radius);
        if (drawLeftTop) {
            path.cubicTo(0, radius / 2, radius / 2, 0, radius, 0);
        } else {
            path.lineTo(0, 0);
            path.lineTo(radius, 0);
        }
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void refreshView() {
        invalidate();
    }
}
