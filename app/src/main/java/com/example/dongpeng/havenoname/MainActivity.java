package com.example.dongpeng.havenoname;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.dongpeng.havenoname.adapter.MyViewPagerAdapter;
import com.example.dongpeng.havenoname.base.BaseActivity;
import com.example.dongpeng.havenoname.home.HomeFragment;
import com.example.dongpeng.havenoname.my.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private BottomNavigationBar bottom_navigation_bar;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//顶部状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部导航栏透明
        }
        setContentView(R.layout.main);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        for (int i = 0; i < 3; i++) {
            fragments.add(new MyFragment());
        }
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyViewPagerAdapter(getFragmentManager(), fragments));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottom_navigation_bar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottom_navigation_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.setActiveColor(R.color.colorPrimary);
//                .setInActiveColor(R.color.colorGray);
////                .setBarBackgroundColor(Color.YELLOW);
        bottom_navigation_bar
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "音乐"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "电影"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "我的"))
                .initialise();
//        bottom_navigation_bar.selectTab();
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
