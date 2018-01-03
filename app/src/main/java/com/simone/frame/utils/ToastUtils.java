package com.simone.frame.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/24 0024.
 * 控制toast不会叠加出现
 */
public class ToastUtils {
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    /**
     * 短时间显示
     */
    public static void showShort(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    /**
     * 长时间显示
     */
    public static void showLong(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }
    /**
     * 因为一般提示信息都是放在strings.xml中，所以为了方便使用
     * @param mContext
     * @param resId
     * @param duration
     */
    public static void showToast(Context mContext, int resId, int duration) {
        showShort(mContext, mContext.getResources().getString(resId), duration);
    }
}
