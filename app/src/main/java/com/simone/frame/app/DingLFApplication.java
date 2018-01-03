package com.simone.frame.app;

import android.app.Application;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import com.simone.frame.R;
import com.weavey.loading.lib.LoadingLayout;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;



/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class DingLFApplication extends Application {
    public static String mDetailContent="";

    private static DingLFApplication mDingLFApplication;
    public static DingLFApplication getInstance(){
        return mDingLFApplication;
    }
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new BezierRadarHeader(context);
                return new ClassicsHeader(context);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mDingLFApplication=this;
        initStatusLayout();
        StyleManager s = new StyleManager();
//在这里调用方法设置s的属性
//code here...
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);
        LoadingDialog.initStyle(s);

    }

    /**
     * 初始化状态布局
     */
    private void initStatusLayout() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setEmptyImage(R.mipmap.icon_404)
                .setAllTipTextSize(14)
                .setReloadButtonText("点击刷新")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.color_4a4a4a)
                .setReloadButtonWidthAndHeight(150,40);
    }


}

