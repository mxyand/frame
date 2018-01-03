package com.simone.frame.ui.fragment;

import android.view.View;

import com.simone.frame.R;
import com.simone.frame.mvp.base.MvpFragment;
import com.simone.frame.mvp.persenter.ShopCarFragmentPersenter;
import com.simone.frame.mvp.view.IShopCarFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class ShopCarFragment extends MvpFragment<ShopCarFragmentPersenter> implements IShopCarFragment {


    Unbinder unbinder;


    public static ShopCarFragment newInstance() {
        return new ShopCarFragment();
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);

    }





    @Override
    protected int setContentView() {
        return R.layout.shopcar_fragment_layout;
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }



    @Override
    public void initActionBar() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError(String reason) {

    }

    @Override
    public void showNoNetwork() {

    }



    @Override
    public void refreshAndLoadFinish() {

    }

    @Override
    public void isLoadAll(boolean isAll) {

    }

    @Override
    public void deleteSuccess(int position) {

    }

    @Override
    protected ShopCarFragmentPersenter createPresenter() {
        return new ShopCarFragmentPersenter(this, mActivity);
    }

}
