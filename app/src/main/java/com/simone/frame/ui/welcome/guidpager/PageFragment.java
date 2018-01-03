package com.simone.frame.ui.welcome.guidpager;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simone.frame.R;
import com.simone.frame.ui.HomePageActivity;


public class PageFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int index = args.getInt("index");
        int layoutId = args.getInt("layoutId");
        int count = args.getInt("count");
        rootView = inflater.inflate(layoutId, null);

        // 滑动到最后一页有点击事件
        if (index == count - 1) {
            rootView.findViewById(R.id.id_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GuideUtils.setIsGuided(getActivity(), getVersionCode());
                    HomePageActivity.startAct(getContext());
                    getActivity().finish();
                }
            });
        } else {
            rootView.findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GuideUtils.setIsGuided(getActivity(), getVersionCode());
                    HomePageActivity.startAct(getContext());
                    getActivity().finish();
                }
            });
        }

        return rootView;
    }

    private int getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = getActivity().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packInfo.versionCode;
        return versionCode;
    }
}
