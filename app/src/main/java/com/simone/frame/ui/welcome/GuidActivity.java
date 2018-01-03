package com.simone.frame.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.simone.frame.R;
import com.simone.frame.base.BaseSupportActivity;
import com.simone.frame.ui.welcome.guidpager.PageFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GuidActivity extends BaseSupportActivity {


    @BindView(R.id.pager)
    PageFrameLayout mPager;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, GuidActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        mPager.setUpViews(new int[]{
                R.layout.page_tab1,
                R.layout.page_tab2,
                R.layout.page_tab3,
                R.layout.page_tab4,
        }, R.drawable.shape_dot_eb6f48, R.drawable.shape_dot_gray);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
