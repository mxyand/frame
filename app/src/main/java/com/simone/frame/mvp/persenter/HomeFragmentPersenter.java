package com.simone.frame.mvp.persenter;

import android.content.Context;

import com.simone.frame.mvp.base.BasePresenter;
import com.simone.frame.mvp.view.IHomeFragment;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class HomeFragmentPersenter extends BasePresenter<IHomeFragment> {

    private int mPage = 1;
    public static final String SIZE = "10";

    public HomeFragmentPersenter(IHomeFragment iHomeFragment, Context context) {
        super(iHomeFragment, context);
    }

    @Override
    public void getData() {
    }


}
