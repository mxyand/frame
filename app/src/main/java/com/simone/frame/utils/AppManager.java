package com.simone.frame.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author BiHaidong
 * @version 1.0
 * @created 2015-9-21
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static Stack<Activity> activityMainStack;
    private static AppManager mInstance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null)
            activityStack = new Stack<Activity>();
        activityStack.add(activity);
    }

    /**
     * 添加Activity到堆栈
     */
    public void addMainActivity(Activity activity) {
        if (activityMainStack == null)
            activityMainStack = new Stack<Activity>();
        activityMainStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }

    }

    /**
     * 结束栈顶的三个页面
     */
    public void finishThreeActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++)
                if (null != activityStack.get(i))
                    if (i == activityStack.size() - 3 || i == activityStack.size() - 2 || i == activityStack.size() - 1) {
                        activityStack.get(i).finish();
                    }

        }
    }

    /**
     * 结束栈顶的2 个页面
     */
    public void finishTwoActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++)
                if (null != activityStack.get(i))
                    if (i == activityStack.size() - 2 || i == activityStack.size() - 1) {
                        activityStack.get(i).finish();
                    }

        }
    }

    /**
     * 结束栈顶的1 个页面
     */
    public void finishOneActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++)
                if (null != activityStack.get(i))
                    if (i == activityStack.size() - 1) {
                        activityStack.get(i).finish();
                    }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++)
                if (null != activityStack.get(i))
                    activityStack.get(i).finish();
        }
        activityStack.clear();
    }

    /**
     * 结束掉主Activity
     */
    public void finishMainActivity() {
        for (int i = 0, size = activityMainStack.size(); i < size; i++)
            if (null != activityMainStack.get(i))
                activityMainStack.get(i).finish();
        activityMainStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 退出程序
     */
    private long exitTime = 0;

    public void exit(Context context) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showShort(context, "再按一次退出应用", 2000);
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(context);
        }
    }

    /**
     * 结束除了此activity之外的所有
     */
    public void finishActivityExitOne(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
}
