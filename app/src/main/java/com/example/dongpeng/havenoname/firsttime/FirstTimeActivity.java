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
import com.example.dongpeng.havenoname.acitivity.WelcomeActivity;

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
        setContentView(R.layout.welcome);
        vp = (ViewPager) findViewById(R.id.vp);
        ImageView iv=new ImageView(FirstTimeActivity.this);
        iv.setImageResource(R.mipmap.ccc);
        views.add(iv);
        iv.setImageResource(R.mipmap.aaa);
        views.add(iv);
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

    }
}
