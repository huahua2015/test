package com.example.bitmapcache;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import androidx.annotation.RequiresApi;

public class ImageCache {
    private final String TAG= ImageCache.class.getName();
    private static ImageCache imageCache = new ImageCache();
    private Context mContext;
    //bitmap对象复用池
    private Set<WeakReference<Bitmap>> mReusablePool;
    private LruCache<String, Bitmap> mMemoryCache;
    //先要下载DiskLruCache的源码
    private DiskLruCache mDiskLruCache;
    private ReferenceQueue<Bitmap> mReferenceQueue;
    private boolean mExit;
    private Thread mRqThread;

    public static ImageCache getInstance() {
        return imageCache;
    }

    public void init(Context context, String diskCacheDir) {
        mContext = context;
        mReusablePool = Collections.synchronizedSet(new HashSet<WeakReference<Bitmap>>());
        //计算当前进程的内存大小
        ActivityManager am = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE);
        //单位是megabytes
        int memory = am.getMemoryClass();
        Log.d(TAG,"memory="+memory);
        //参数size单位是byte,
        mMemoryCache = new LruCache<String, Bitmap>(memory * 1024 * 1024 / 10) {
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                //oldValue就是从LRU中拿出来的bitmap
                //当一个bitmap被lrucache移除时，考虑复用这个bitmap对象
                Log.d(TAG,"key="+key+",evicted="+evicted+",oldValue="+oldValue);
                if (oldValue.isMutable()) {
                    //把引用添加到复用池，同时注册到一个引用队列上，在这个引用被引用的对象被GC时，
                    // 这个引用会被追加到这个队列中
                    mReusablePool.add(new WeakReference<Bitmap>(oldValue, getReferenceQueue()));
                } else {
                    oldValue.recycle();
                }
            }

           // @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一张图片的大小
               // Log.d(TAG,"value.getAllocationByteCount()="+value.getAllocationByteCount());
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
                    return value.getAllocationByteCount();
                }
                return value.getByteCount();
            }
        };

      /*  try {
            mDiskLruCache = DiskLruCache.open(new File(diskCacheDir),
                    BuildConfig.VERSION_CODE,
                    1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    //使用这个队列的目标，就是跟踪对象的回收，来检测内存泄漏
    private ReferenceQueue<Bitmap> getReferenceQueue() {
        if (mReferenceQueue == null) {
            mReferenceQueue = new ReferenceQueue<>();
            mRqThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //确保线程能正常退出。
                    while (!isExit() &&
                            !Thread.currentThread().isInterrupted()) {
                        try {
                            //remove带阻塞功能的
                            Reference<? extends Bitmap> rmove = mReferenceQueue.remove();
                            //如果一个引用-引用的对象被回收了，那么这个引用本身也应该释放掉。
                            Bitmap bitmap = rmove.get();
                            Log.d(TAG,"getReferenceQueue,bitmap="+bitmap);
                            if (bitmap != null && !bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            });
            mRqThread.start();
        }
        return mReferenceQueue;
    }

    public boolean isExit() {
        return mExit;
    }

    public void setExit(boolean mExit) {
        this.mExit = mExit;
    }

    public void interruptThread() {
        setExit(true);
        mRqThread.interrupt();
        Log.d(TAG,"isInterrupted="+mRqThread.isInterrupted());
    }

    //把bitmap放入缓存
    public void putBitmap2Memory(String key, Bitmap bitmap) {
        mMemoryCache.put(key, bitmap);
    }

    //从缓存读取bitmap
    public Bitmap getBitmapFromMemory(String key) {
        return mMemoryCache.get(key);
    }

    //清空缓存
    public void clearMemory() {
        mMemoryCache.evictAll();
    }

    //把bitmap放入磁盘
    public void putBitmap2Disk(String key, Bitmap bitmap) {
        DiskLruCache.Snapshot snapshot = null;
        OutputStream os = null;
        //先判断磁盘中有没有，如果没有就写入
        try {
            snapshot = mDiskLruCache.get(key);
            if (snapshot == null) {
                DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                //写入文件前，做压缩
                os = editor.newOutputStream(0);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, os);
                editor.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //从磁盘读取bitmap
    public Bitmap getBitmapFromDisk(String key, Bitmap reusable) {
        DiskLruCache.Snapshot snapshot = null;
        Bitmap bitmap = null;
        try {
            snapshot = mDiskLruCache.get(key);
            if (snapshot == null) {
                return null;
            }
            InputStream is = snapshot.getInputStream(0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            //重用bitmap的内存
            options.inMutable = true;
            options.inBitmap = reusable;
            bitmap = BitmapFactory.decodeStream(is, null, options);
            if (bitmap != null) {
                mMemoryCache.put(key, bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //判断bitmap的内存是否可以重用，根据inBitmap的注释，将要解码的bitmap的内存要小于等于
    // 被重用bitmap的内存。
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Bitmap getReusable(int width, int height, int inSampleSize) {
        Bitmap reusable = null;
        Iterator<WeakReference<Bitmap>> iterator = mReusablePool.iterator();
        while (iterator.hasNext()) {
            Bitmap bitmap = iterator.next().get();
            if (bitmap != null) {
                if (checkReusableBitmap(bitmap, width, height, inSampleSize)) {
                    reusable = bitmap;
                    iterator.remove();
                    break;
                }
            } else {
                iterator.remove();
            }
        }
        return reusable;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkReusableBitmap (Bitmap bitmap, int width, int height, int inSampleSize) {
        if (inSampleSize > 1) {
            width /= inSampleSize;
            height /= inSampleSize;
        }

        int byteCount = width * height * getBytesPerPixel(bitmap.getConfig());
        return byteCount <= bitmap.getAllocationByteCount();
    }

    //单个像素占的字节数
    private int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888) {
            return 4;
        }
        return 2;
    }
}