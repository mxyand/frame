package com.simone.frame.ui.welcome;

import android.os.Bundle;

import com.simone.frame.R;
import com.simone.frame.mvp.base.MvpActivity;
import com.simone.frame.mvp.persenter.SplashPersenter;
import com.simone.frame.mvp.view.ISplashAct;
import com.simone.frame.ui.HomePageActivity;


public class SplashActivity extends MvpActivity<SplashPersenter> implements ISplashAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mvpPresenter.getData();
    }

    @Override
    protected SplashPersenter createPresenter() {
        return new SplashPersenter(this, mActivity);
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
    public void jumpGuid() {
        GuidActivity.startAct(mActivity);
        finish();
    }

    @Override
    public void jumpHome() {
        HomePageActivity.startAct(mActivity);
        finish();
    }
}
