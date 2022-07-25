package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestPath extends View {
    private int mWith;
    private int mHeight;
    private Paint mPaint;
    private Paint mPaintPoint;
    private int count=0;
    public static final int MOVE=0X23;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MOVE:
                    count+=2;
                    if (count==80){
                        count=0;
                    }
                    invalidate();//刷新数据
                    handler.sendEmptyMessageDelayed(MOVE, 200);
                    break;
                default:
                    break;
            }
        }
    };
    public TestPath(Context context) {
        super(context);
    }

    public TestPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaintPoint=new Paint();

        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30);

        mPaintPoint.setColor(Color.RED);
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStrokeWidth(10);
        mPaintPoint.setStyle(Paint.Style.STROKE);
        mPaintPoint.setTextSize(30);

//      发送空消息，启动handler线程
        handler.sendEmptyMessage(MOVE);
    }

    public TestPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWith = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(mWith,mHeight);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        画一个三角形
        Path PathTriangle=new Path();
        PathTriangle.moveTo(10,340);//移动到一个点
        PathTriangle.lineTo(310,340);//画线
        PathTriangle.lineTo(150,120);
        PathTriangle.close();//将图形封闭
//        画一个圆
        Path PathCircle=new Path();
//        最后一个参数是设置顺时针还有逆时针
        PathCircle.addCircle(500, 200, 100, Path.Direction.CCW);
//        画出path描述的图形
        canvas.drawPath(PathTriangle,mPaint);
        canvas.drawPath(PathCircle,mPaint);
//        在path描述的图形上面加文字
        canvas.drawTextOnPath("啊我是文字我是文字我是文字", PathCircle, 300, 0, mPaint);

//        画贝塞尔曲线
        Path pathQuad=new Path();
        pathQuad.moveTo(100,450);
//        一个控制点，一个结束点
        pathQuad.quadTo(300,350,500,450);
        canvas.drawPath(pathQuad, mPaint);
        canvas.drawPoint(100, 450, mPaintPoint);
        canvas.drawPoint(300, 350, mPaintPoint);
        canvas.drawPoint(500, 450, mPaintPoint);

//        绘制可动的波浪线
        Path pathMove=new Path();
//        拖动波浪线的横坐标即可实现波浪效果
        pathMove.reset();//每次都要重置波浪线的起始位置
        pathMove.moveTo(count, 530);
        for(int i=0;i<10;i++) {
//        rQuadTo将当前的点作为原点使用，每使用一次都将之前的结束点作为原点，所以这个周期为长度80
            pathMove.rQuadTo(20, 10, 40, 0);
            pathMove.rQuadTo(20, -10, 40, 0);
        }
        canvas.drawPath(pathMove,mPaint);
//        画上框看波浪更明显
        canvas.drawCircle(300,530,100,mPaintPoint);
    }
}
