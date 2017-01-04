package com.example.dongpeng.havenoname.base;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class BaseActivity extends Activity {
    private Toast toast;
    public void showToast(String msg){
        if (toast==null){
            toast= Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    };
}
