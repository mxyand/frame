package com.simone.frame.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.simone.frame.base.LazyLoadFragment;


/**
 * Created by Administrator on 2017/11/1 0001.
 */

public abstract class MvpFragment<P extends BasePresenter> extends LazyLoadFragment {
    protected P mvpPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }

    /**
     * 初始化presenter的抽象方法
     *
     * @return
     */
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detach();
        }
    }

}
