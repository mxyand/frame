package com.simone.frame.ui.fragment;


import android.view.View;

import com.simone.frame.R;
import com.simone.frame.mvp.base.MvpFragment;
import com.simone.frame.mvp.persenter.HomeFragmentPersenter;
import com.simone.frame.mvp.view.IHomeFragment;

import butterknife.ButterKnife;

import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/11/9 0009.
 * 首页
 */

public class HomeFragment extends MvpFragment<HomeFragmentPersenter> implements IHomeFragment {


    Unbinder unbinder;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);


    }


    @Override
    protected int setContentView() {
        return R.layout.home_fragment_layout;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

        showToastMsgShort(reason);

    }

    @Override
    public void showNoNetwork() {

    }


    /**
     * 初始化tablayout和viewpager
     */

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }







    /**
     * 上啦加载完成
     */
    @Override
    public void loadMoreFinish(Boolean success) {

    }

    /**
     * 已经加载全部数据
     */
    @Override
    public void loadMoreAllData(Boolean isAll) {

    }

    @Override
    protected HomeFragmentPersenter createPresenter() {
        return new HomeFragmentPersenter(this, mActivity);
    }

}
