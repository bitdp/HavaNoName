package com.example.dongpeng.havenoname.utils;

import android.util.Log;
import com.example.dongpeng.havenoname.BuildConfig;

/**
 * Created by dongpeng on 2017/1/9.
 */

public class LogUtil {
    public static void d(String msg){
        if (BuildConfig.DEBUG){
            Log.d("------", msg);
        }
    }
    public static void e(String msg){
        if (BuildConfig.DEBUG){
            Log.e("------", msg);
        }
    }
}
