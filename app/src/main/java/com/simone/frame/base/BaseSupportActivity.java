package com.simone.frame.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.simone.frame.utils.AppManager;
import com.simone.frame.utils.HideKeyBroadUtils;
import com.simone.frame.utils.ToastUtils;


/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class BaseSupportActivity extends AppCompatActivity {
    public Activity mActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        //适配状态栏颜色
//        StatusBarUtil.StatusBarLightMode(mActivity,true);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 实现子类点击空白关闭软键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        HideKeyBroadUtils.hide(mActivity,ev);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 弹出toast 显示时长short
     * @param pMsg
     */
    protected void showToastMsgShort(String pMsg) {
        ToastUtils.showShort(this,pMsg,1000);
    }
    /**
     * 弹出toase 显示时长long
     * @param pMsg
     */
    protected void showToastMsgLong(String pMsg) {
        ToastUtils.showLong(this,pMsg,2000);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


}
