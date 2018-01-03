package com.simone.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/11/12.
 * 点击空白区域关软键盘
 */

public class HideKeyBroadUtils {
    private HideKeyBroadUtils(){

    }

    /**
     * 隐藏软键盘
     * @param activity
     * @param ev
     */
    public static void hide(Activity activity, MotionEvent ev){
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = activity.getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(activity,view.getWindowToken());
            }
        }
    }

    // 判定是否需要隐藏
    private static boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private static void HideSoftInput(Activity activity,IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
