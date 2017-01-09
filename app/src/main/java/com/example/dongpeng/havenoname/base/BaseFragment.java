package com.example.dongpeng.havenoname.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dongpeng on 2017/1/9.
 */

public abstract class BaseFragment extends Fragment {
    private boolean isViewCreated;
    private boolean isDateLoaded;//是否加载过数据

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        initView(view);
        isViewCreated = true;
        return view;
    }

    protected abstract void initView(View view);
    public abstract int getLayout();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isDateLoaded) {
            loadDate();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadDate();
        }
    }

    private void loadDate() {
        isDateLoaded = true;
    }
}
