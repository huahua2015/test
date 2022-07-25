package com.example.bitmapcache;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageResize {
    public static Bitmap resizeBitmap(Context context, int id, int desiredWidth, int desireHeight,
                                      boolean alpha, Bitmap reusable) {
        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        //仅解析out参数
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,id, options);
        int width = options.outWidth;
        int height = options.outHeight;
        //计算缩放比例
        options.inSampleSize = calculateInSampleSize(width,height,desiredWidth,desireHeight);
        //如果不需要透明，重新设置颜色配置
        if (!alpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        options.inJustDecodeBounds = false;

        //复用Bitmap
        options.inMutable = true;
        options.inBitmap = reusable;

        return BitmapFactory.decodeResource(resources, id, options);
    }

    public static int calculateInSampleSize(int originalW, int originalH, int desiredW, int desiredH) {
        int inSampleSize = 1;
        //inSampleSize是2的指数次幂
        if (originalW > desiredW && originalH > desiredH) {
            inSampleSize = 2;
            while (originalW / inSampleSize > desiredW && originalH / inSampleSize > desiredH) {
                inSampleSize *=2;
            }
        }
        return inSampleSize;
    }
}
