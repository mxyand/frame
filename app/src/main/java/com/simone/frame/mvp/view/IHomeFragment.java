package com.simone.frame.mvp.view;


import com.simone.frame.mvp.base.BaseView;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public interface IHomeFragment extends BaseView {
//   // void setTabData(List<HomeBean.ThingCategoryBean> thingCategory);
   // void setRecyclerData(List<ShopBean.CategoryThingBean.ItemsBean> items, Boolean loadMore);
    void loadMoreFinish(Boolean success);
    void loadMoreAllData(Boolean isAll);
}
