package com.example.myview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class UserMainView  extends View {
    private UserMainTouchListener clickListener;
   float  toolbar=0;
    float statusbar=(float)getStatusBarHeight(super.getContext());
    Resources resources = this.getResources();
    DisplayMetrics dm = resources.getDisplayMetrics();
    float width = dm.widthPixels;
    float height = dm.heightPixels;

    public UserMainView(Context context) {
        super(context);
    }

    public UserMainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    public interface UserMainTouchListener{          //五大功能入口
        void ClinckOne();                     //今天元气满满的
        void ClickTwo();
        void ClickThree();
        void ClickFour();
        void ClickFive();
    }
    public void setClickListener(UserMainTouchListener clickListener) {
        this.clickListener=clickListener;

    }




    protected void onDraw(Canvas canvas){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        //float[]pts={0,toolbar,width,toolbar/3+(height/3)*2,width,toolbar,width/2,(toolbar/3)*2+height/3,width/2,(toolbar/3)*2+height/3,0,height,0,height,width,toolbar/3+(height/3)*2};
        float[]pts={0,0,width,toolbar/3+(height/3)*2-toolbar,width,0,width/2,(toolbar/3)*2+height/3-toolbar,width/2,(toolbar/3)*2+height/3-toolbar,0,height-toolbar-statusbar,0,height-toolbar-statusbar,width,toolbar/3+(height/3)*2-toolbar};
        canvas.drawLines(pts,paint);
        Paint text=new Paint();
        text.setTextSize(30);
        text.setStrokeWidth(5);
        text.setColor(Color.WHITE);
        canvas.drawText("相约运动",3*width/7,toolbar/6+height/6,text);
        canvas.drawText("我的朋友",width/6,2*toolbar/3+height/3,text);
        canvas.drawText("场馆运动",2*width/3,toolbar/4+height/3+5,text);
        canvas.drawText("运动百货",width/2,3*height/5,text);
        canvas.drawText("咨讯天地",2*width/3,toolbar+5*height/7,text);

    }
    public boolean onTouchEvent(MotionEvent motionEvent){
        double x=(double)motionEvent.getRawX();
        double y=(double)(motionEvent.getRawY()-toolbar-statusbar);
        double l1=2*(height-toolbar)/(3*width)*x;
        double l2=2*(toolbar-height)/(3*width)*x-2*(toolbar-height)/3;
        double l3=(4*(toolbar-height)/(3*width)+2*statusbar/width)*x+height-toolbar-statusbar;
        double l4=((toolbar-height)/(3*width)+statusbar/width)*x+height-toolbar-statusbar;
        int action=motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (y>0&&y<l1&&y<l2)
                    clickListener.ClinckOne();
                if (x>0&&y>l1&&y<l3)
                    clickListener.ClickTwo();
                if (x<width&&y>l2&&y<l1)
                    clickListener.ClickThree();
                if (y>l3&&y>l1&&y<l4)
                    clickListener.ClickFour();
                if (x<width&&y<height-toolbar-statusbar&&y>l4)
                    clickListener.ClickFive();
                break;
        }
        return true;
    }


}
