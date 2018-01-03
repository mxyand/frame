package com.simone.frame.ui.fragment;

import android.view.View;


import com.simone.frame.R;
import com.simone.frame.app.DingLFApplication;
import com.simone.frame.mvp.base.MvpFragment;
import com.simone.frame.mvp.persenter.MineFragmentPersenter;
import com.simone.frame.mvp.view.IMinefragment;
import com.simone.frame.utils.spreference.SharedPreferencesHelper;
import com.simone.frame.utils.spreference.SharedPreferencesTag;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class MineFragment extends MvpFragment<MineFragmentPersenter> implements IMinefragment {


    Unbinder unbinder;
    private SharedPreferencesHelper mHelper;
    private boolean mBooleanLogin;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);
        mHelper = SharedPreferencesHelper.getInstance(DingLFApplication.getInstance());
    }

    @Override
    public void onResume() {
        super.onResume();
        mBooleanLogin = mHelper.getBooleanValue(SharedPreferencesTag.LOGIN_BOOLEAN);
        if (mBooleanLogin) {
            String nickname = mHelper.getStringValue(SharedPreferencesTag.USER_NICKNAME);
            String img = mHelper.getStringValue(SharedPreferencesTag.USER_HEAD_IMG);

        } else {

        }
    }

    @Override
    protected int setContentView() {
        return R.layout.mine_fragment_layout;
    }

    @Override
    protected void lazyLoad() {
        mvpPresenter.getData();
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

    }

    @Override
    public void showNoNetwork() {

    }


    @Override
    public void setInfo() {
        List<String> list = new ArrayList<>();
        list.add("");

    }

    @Override
    protected MineFragmentPersenter createPresenter() {
        return new MineFragmentPersenter(this, mActivity);
    }
}
