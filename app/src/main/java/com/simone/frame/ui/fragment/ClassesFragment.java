package com.simone.frame.ui.fragment;

import android.view.View;

import com.simone.frame.R;
import com.simone.frame.mvp.base.MvpFragment;
import com.simone.frame.mvp.persenter.ClassesFragmentPersenter;
import com.simone.frame.mvp.view.IClassesFragment;
import com.weavey.loading.lib.LoadingLayout;


import butterknife.ButterKnife;

import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class ClassesFragment extends MvpFragment<ClassesFragmentPersenter> implements IClassesFragment, LoadingLayout.OnReloadListener {

    Unbinder unbinder;


    public static ClassesFragment newInstance() {
        return new ClassesFragment();
    }

    @Override
    protected void initView(View rootView) {
        unbinder = ButterKnife.bind(this, rootView);



    }



    @Override
    protected int setContentView() {
        return R.layout.classes_fragment_layout;
    }

    @Override
    protected void lazyLoad() {
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
    public void loadMoreFinish(Boolean success) {

    }

    @Override
    public void loadMoreAllData(Boolean isAll) {

    }

    @Override
    protected ClassesFragmentPersenter createPresenter() {
        return new ClassesFragmentPersenter(this, mActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onReload(View v) {
        mvpPresenter.getData();
    }
}
