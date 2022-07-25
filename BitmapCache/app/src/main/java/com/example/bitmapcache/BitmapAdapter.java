package com.example.bitmapcache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class BitmapAdapter extends RecyclerView.Adapter<BitmapAdapter.BitmapViewHolder> {
    private Context mContext;
    private final String TAG = BitmapAdapter.class.getName();

    public BitmapAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public BitmapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(
                R.layout.rv_item, null, false);
        BitmapViewHolder bitmapViewHolder = new BitmapViewHolder(item);
        return bitmapViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull BitmapViewHolder holder, int position) {
        Log.d("BitmapAdapter","position="+position);
        //应用缓存，及复用Bitmap，首先从内存缓存读取
        Bitmap bitmap = ImageCache.getInstance().getBitmapFromMemory(String.valueOf(position));
        if (bitmap != null) {
            Log.d(TAG, "memory cache in use..." + bitmap);
        }

        if (bitmap == null) {
            //先检查是否有可复用内存,为方便测试，宽高比实际加载的bitmap稍小一点
            Bitmap reusable = ImageCache.getInstance().getReusable(298, 298, 1);
            if (reusable != null) {
                Log.d(TAG, "reusable bitmap in use..." + reusable);
            }
            //如果reusable是null，在BitmapFactory.cpp的处理中会忽略掉重用
            bitmap = ImageCache.getInstance().getBitmapFromDisk(String.valueOf(position), reusable);
            if (bitmap != null) {
                Log.d(TAG, "disk bitmap in use..." + bitmap);
            }
            //初次使用，肯定要先加载，从本地或者网络，然后放入缓存，磁盘
            if (bitmap == null) {
                bitmap = ImageResize.resizeBitmap(mContext, R.drawable.ic_launcher_background,
                        300, 300, false ,reusable);
                ImageCache.getInstance().putBitmap2Memory(String.valueOf(position) , bitmap);
                ImageCache.getInstance().putBitmap2Disk(String.valueOf(position), bitmap);
            }
        }
        if (bitmap != null) {
            holder.iv.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        //仅做测试，总共500条数据，实际根据列表size来定
        return 500;
    }

    class BitmapViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        public BitmapViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
