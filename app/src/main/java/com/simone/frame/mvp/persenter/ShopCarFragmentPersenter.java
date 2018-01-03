package com.simone.frame.mvp.persenter;

import android.content.Context;

import com.simone.frame.mvp.base.BasePresenter;
import com.simone.frame.mvp.view.IShopCarFragment;


/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class ShopCarFragmentPersenter extends BasePresenter<IShopCarFragment> {
    private static final String PAGESIZE="10";
    private int mPage=1;
    public ShopCarFragmentPersenter(IShopCarFragment iShopCarFragment, Context context) {
        super(iShopCarFragment, context);
    }

    @Override
    public void getData() {
    }


}
