package com.simone.frame.ui.welcome.guidpager;

import android.content.Context;

import com.simone.frame.app.DingLFApplication;
import com.simone.frame.utils.spreference.SharedPreferencesHelper;


/**
 * 当前类注释:查询与设置引导界面标志值工具类
 * 主要用于引导界面，APP新版本第一次打开 才会进行使用引导界面
 */
public class GuideUtils {
    private static final String KEY_GUIDE_ACTIVITY="key_guide_activity";
    /**
     * 根据版本code值 判断是否已经引导过了
     * @param context  上下文
     * @param versionCode  版本值
     * @return  引导过返回true,否则返回false
     */
    public static boolean activityIsGuided(Context context, int versionCode){
        SharedPreferencesHelper mSharedPreferencesHelper=SharedPreferencesHelper.getInstance(DingLFApplication.getInstance());
        if(mSharedPreferencesHelper.getIntValue(KEY_GUIDE_ACTIVITY)==versionCode){
            return true;
        }
        return false;
    }
    /**
     * 设置code值，表明已经引导过
     * @param context
     * @param versionCode
     */
    public static void setIsGuided(Context context, int versionCode){
        SharedPreferencesHelper mSharedPreferencesHelper=SharedPreferencesHelper.getInstance(DingLFApplication.getInstance());
        mSharedPreferencesHelper.putIntValue(KEY_GUIDE_ACTIVITY, versionCode);
    }

}
