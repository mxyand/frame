package com.simone.frame.mvp.base;

import android.os.Bundle;

import com.simone.frame.base.BaseSupportActivity;


/**
 * mvp父类，功能activity都继承自该类实现presenter的初始化与销毁，
 * 防止p一直持有activity导致内存溢出
 * @param <P>
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseSupportActivity {
    protected P mvpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter =createPresenter();
    }

    /**
     * 初始化presenter的抽象方法
     * @return
     */
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter !=null){
            mvpPresenter.detach();
        }
    }
}
