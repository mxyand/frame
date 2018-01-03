package com.simone.frame.mvp.view;


import com.simone.frame.mvp.base.BaseView;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public interface IShopCarFragment extends BaseView {
   // void setInfo(List<MyShopCarResultBean.CartListBean.ItemsBean> items, boolean isloadmore);
    void refreshAndLoadFinish();
    void isLoadAll(boolean isAll);
    void deleteSuccess(int position);
}
