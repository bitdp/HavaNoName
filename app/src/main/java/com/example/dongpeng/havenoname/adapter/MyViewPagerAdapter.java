package com.example.dongpeng.havenoname.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by dongpeng on 2017/1/9.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    List<android.app.Fragment> fragments;
    FragmentManager fm;
    public MyViewPagerAdapter(FragmentManager fm, List<android.app.Fragment> fragments) {
        super(fm);
        this.fm=fm;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
