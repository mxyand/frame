package com.simone.frame.mvp.persenter;

import android.content.Context;

import com.simone.frame.mvp.base.BasePresenter;
import com.simone.frame.mvp.view.IMinefragment;


/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class MineFragmentPersenter extends BasePresenter<IMinefragment> {

    public MineFragmentPersenter(IMinefragment iMinefragment, Context context) {
        super(iMinefragment, context);
    }

    @Override
    public void getData() {
        mvpView.setInfo();
    }
}
