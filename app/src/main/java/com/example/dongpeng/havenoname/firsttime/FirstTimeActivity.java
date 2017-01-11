package com.example.dongpeng.havenoname.firsttime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dongpeng.havenoname.MainActivity;
import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.adapter.MyWelcomePagerAdapter;
import com.example.dongpeng.havenoname.utils.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongpeng on 2017/1/10.
 */

public class FirstTimeActivity extends Activity {
    private ViewPager vp;
    private List<View> views=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_lay);
        SharedPreferenceUtil.getInstance(FirstTimeActivity.this).saveToSp("noFirst",true);
        vp = (ViewPager) findViewById(R.id.vp);
        ImageView iv1=new ImageView(FirstTimeActivity.this);
        iv1.setImageResource(R.mipmap.ccc);
        iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        views.add(iv1);
        ImageView iv2=new ImageView(FirstTimeActivity.this);
        iv2.setImageResource(R.mipmap.aaa);
        iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        views.add(iv2);
        View view= LayoutInflater.from(FirstTimeActivity.this).inflate(R.layout.wel_img,null);
        view.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstTimeActivity.this, MainActivity.class);
                startActivity(intent);
                FirstTimeActivity.this.finish();
            }
        });
        views.add(view);
        vp.setAdapter(new MyWelcomePagerAdapter(views));
    }
}
