package com.simone.frame.mvp.view;

import com.simone.frame.mvp.base.BaseView;




/**
 * Created by Administrator on 2017/11/12.
 */

public interface IClassesFragment extends BaseView {
  //  void setLeftRecycler(List<HomeBean.ThingCategoryBean> list);
   // void setRightRecycler(List<ShopBean.CategoryThingBean.ItemsBean> items, Boolean loadMore);
    void loadMoreFinish(Boolean success);
    void loadMoreAllData(Boolean isAll);
}
