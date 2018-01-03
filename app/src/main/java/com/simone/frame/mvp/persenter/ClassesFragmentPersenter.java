package com.simone.frame.mvp.persenter;

import android.content.Context;

import com.simone.frame.mvp.base.BasePresenter;
import com.simone.frame.mvp.view.IClassesFragment;


/**
 * Created by Administrator on 2017/11/12.
 */

public class ClassesFragmentPersenter extends BasePresenter<IClassesFragment> {


    public ClassesFragmentPersenter(IClassesFragment iClassesFragment, Context context) {
        super(iClassesFragment, context);
    }

    /**
     * 分类数据
     */
    @Override
    public void getData() {


    }


}
