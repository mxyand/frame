package com.simone.frame.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.simone.frame.R;
import com.simone.frame.base.BaseSupportActivity;
import com.simone.frame.ui.fragment.ClassesFragment;
import com.simone.frame.ui.fragment.HomeFragment;
import com.simone.frame.ui.fragment.MineFragment;
import com.simone.frame.ui.fragment.ShopCarFragment;
import com.simone.frame.utils.AppManager;
import com.simone.frame.views.MyViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePageActivity extends BaseSupportActivity {

    @BindView(R.id.home)
    RadioButton mHome;
    @BindView(R.id.classes)
    RadioButton mClasses;
    @BindView(R.id.shopCar)
    RadioButton mShopCar;
    @BindView(R.id.mine)
    RadioButton mMine;
    @BindView(R.id.tabGroup)
    RadioGroup mTabGroup;

    @BindView(R.id.cvpMain)
    MyViewPager cvpMain;

    public static ViewPager viewPager;

    private List<Fragment> mFragment = new ArrayList<>();
    public static void startAct(Context context) {
        Intent intent = new Intent(context, HomePageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        viewPager=cvpMain;
        cvpMain.setPagingEnabled(false);

        if (savedInstanceState != null) {  // “内存重启”时调用
            nomalStart();
        } else {
            nomalStart();
        }
        initTab();

    }
    private void nomalStart() {


        mFragment.add(new HomeFragment());
        mFragment.add(new ClassesFragment());
        mFragment.add(new ShopCarFragment());
        mFragment.add(new MineFragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        cvpMain.setAdapter(fragmentPagerAdapter);
        cvpMain.setOffscreenPageLimit(4);



    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    private void initTab() {
        mTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.home:
                        cvpMain.setCurrentItem(0, false);
                        break;
                    case R.id.classes:
                        cvpMain.setCurrentItem(1, false);
                        sendEvent(2);
                        break;
                    case R.id.shopCar:
                        cvpMain.setCurrentItem(2, false);
                        sendEvent(3);
                        break;
                    case R.id.mine:
                        cvpMain.setCurrentItem(3, false);
                        sendEvent(4);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private boolean homeLoaded = true, classLoaded = false, shopcarLoaded = false, mineLoaded = false;//页面是否加载过

    private void sendEvent(int type) {
        switch (type) {
            case 2:
                if (!classLoaded) {
                    //EventBus.getDefault().post(new LoadClassesData());
                }
                classLoaded = true;
                break;
            case 3:
                if (!shopcarLoaded) {
                  //  EventBus.getDefault().post(new LoadShopCarData());
                }
                shopcarLoaded = true;
                break;
            case 4:
                if (!mineLoaded) {
                   // EventBus.getDefault().post(new LoadMineData());
                }
                mineLoaded = true;
                break;
        }

    }

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            AppManager.getAppManager().finishAllActivity();
//            System.exit(0);
        }
    }
}
