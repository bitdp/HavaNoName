package com.example.dongpeng.havenoname.acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongpeng.havenoname.MainActivity;
import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.firsttime.FirstTimeActivity;
import com.example.dongpeng.havenoname.utils.LogUtil;
import com.example.dongpeng.havenoname.utils.SharedPreferenceUtil;

/**
 * Created by dongpeng on 2017/1/10.
 */

public class WelcomeActivity extends Activity {
    private ImageView img_wel;
    private TextView tv_wel;
    private int i = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i == -1) {
                LogUtil.e("handler");
                handler.removeCallbacksAndMessages(null);
                if (SharedPreferenceUtil.getInstance(WelcomeActivity.this).getBoolean("noFirst")) {
                    startActivity();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, FirstTimeActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            } else {
                tv_wel.setText("("+i + "s跳过)");
                handler.sendEmptyMessageDelayed(1, 1000);
                i = i - 1;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        img_wel = (ImageView) findViewById(R.id.img_wel);
        tv_wel = (TextView) findViewById(R.id.tv_wel);
        img_wel.setImageResource(R.mipmap.ccc);
        handler.sendEmptyMessageDelayed(1, 1000);
        tv_wel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                startActivity();
            }
        });
    }

    void startActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }
}
