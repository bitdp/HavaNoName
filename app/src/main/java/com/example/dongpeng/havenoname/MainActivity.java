package com.example.dongpeng.havenoname;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.dongpeng.havenoname.base.BaseActivity;
import com.example.dongpeng.havenoname.entity.Person;
import com.example.dongpeng.havenoname.my.MyFragment;

public class MainActivity extends BaseActivity {
    private BottomNavigationBar bottom_navigation_bar;
    private FrameLayout content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        content= (FrameLayout) findViewById(R.id.content);
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setPassword("北京海淀");
        bottom_navigation_bar= (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.setActiveColor(R.color.colorPrimary)
                .setInActiveColor(R.color.colorGray);
//                .setBarBackgroundColor(Color.YELLOW);
        bottom_navigation_bar
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "音乐"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "电影"))
                .addItem(new BottomNavigationItem(R.mipmap.tab_btn_home_default, "我的"))
                .initialise();
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position==3){
                    MyFragment myFragment=new MyFragment();
                    FragmentManager manager=getFragmentManager();
                    FragmentTransaction transaction=manager.beginTransaction();
                    transaction.replace(R.id.content,myFragment);
                    transaction.commit();
                }
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
