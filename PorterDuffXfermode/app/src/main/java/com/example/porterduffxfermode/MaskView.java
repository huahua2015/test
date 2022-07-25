package com.example.porterduffxfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

public class MaskView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private int centerX;//圆心坐标
    private int centerY;
    private float radius;//圆半径
    private final int mDuration = 2000;

    public MaskView(Context context) {
        this(context,null);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setStyle(Paint.Style.FILL);//设置画笔样式为填充
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher);//获取被遮罩的图片
        centerX = bitmap.getWidth()/2;//设置圆心位置
        centerY = bitmap.getHeight()/2;
        radius = 0;//没有水波纹动画的情况下设置为40 如果做动画 从0开始

        ValueAnimator animator = ValueAnimator.ofFloat(0, (float) (Math.hypot(bitmap.getWidth(),bitmap.getHeight())/2));
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(mDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);//关闭硬件加速
        canvas.drawBitmap(bitmap,0,0,paint);//绘制图片
        int layerId = canvas.saveLayer(0,0,bitmap.getWidth(),bitmap.getHeight(),paint,Canvas.ALL_SAVE_FLAG);//保存图层
        paint.setColor(Color.WHITE);//设置画笔颜色
        canvas.drawRect(0,0,bitmap.getWidth(),bitmap.getHeight(),paint);//绘制一个图层盖着我们刚才绘制的图片
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));//PorterDuffXfermode 设置画笔的图形混合模式
        canvas.drawCircle(centerX,centerY,radius,paint);//画圆
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
