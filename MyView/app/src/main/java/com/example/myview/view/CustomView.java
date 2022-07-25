package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

public class CustomView extends View {
    private final Context context;
    Paint paint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    public CustomView(Context context) {

        // TODO Auto-generated constructor stub
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Path mPath=new Path();
        // TODO Auto-generated method stuz
         paint = new Paint();
        canvas.drawCircle(150,150,100,customPaint1(Color.BLUE));//完成
        canvas.drawCircle(370,150,100,customPaint2(Color.BLACK));
        canvas.drawCircle(590,150,100,customPaint3(Color.RED));
        canvas.drawCircle(260,250,100,customPaint4(Color.YELLOW,canvas));
        canvas.drawCircle(480,250,100,customPaint5(Color.GREEN));
       /* paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        mPath.arcTo(200, 500, 400, 700, -225, 225, true);
        mPath.arcTo(400, 500, 600, 700, -180, 225, false);
        mPath.lineTo(400, 842);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawPath(mPath, paint);*/
        super.onDraw(canvas);
    }

    /**
     * 根据传入的颜色配置不同的画笔
     * @param color 颜色
     * @return
     */
    private Paint customPaint1(int color){
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        return paint;
    }
    private Paint customPaint4(int color,Canvas canvas){
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint.setColor(color);
       // paint.setStyle(Paint.Style.STROKE);
       // paint.setStrokeWidth(15);
       // paint.setAntiAlias(true);
        return paint;
    }
    private Paint customPaint2(int color){
        paint.setXfermode(null);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        return paint;
    }
    private Paint customPaint3(int color){
        paint.setXfermode(null);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        return paint;
    }
    private Paint customPaint5(int color){
        paint.setXfermode(null);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        return paint;
    }
}
