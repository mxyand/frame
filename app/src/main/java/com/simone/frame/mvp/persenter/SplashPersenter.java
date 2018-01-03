package com.simone.frame.mvp.persenter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.simone.frame.mvp.base.BasePresenter;
import com.simone.frame.mvp.view.ISplashAct;
import com.simone.frame.utils.GuideUtils;


/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class SplashPersenter extends BasePresenter<ISplashAct> {

    public SplashPersenter(ISplashAct iSplashAct, Context context) {
        super(iSplashAct, context);
    }

    @Override
    public void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (GuideUtils.activityIsGuided(mContext, getVersionCode())) {
                    //to首页
                    mvpView.jumpHome();
                } else {
                    //to引导页
                    mvpView.jumpGuid();

                }
            }
        }, 1500);

    }

    private int getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packInfo.versionCode;
        return versionCode;
    }
}
