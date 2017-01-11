package com.example.dongpeng.havenoname.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dongpeng on 2017/1/11.
 */

public class SharedPreferenceUtil {
    private static Context context;
    private static SharedPreferences sp;
    private static SharedPreferenceUtil util;
    public static SharedPreferenceUtil getInstance(Context c){
        if (util==null){
            context=c;
            sp=context.getSharedPreferences("my",context.MODE_PRIVATE);
            util=new SharedPreferenceUtil();
        }
        return util;
    }
    public <T>void saveToSp(String name,T object){
        if (object instanceof Integer){
            sp.edit().putInt(name, (Integer) object).commit();
        }else if(object instanceof String){
            sp.edit().putString(name, (String) object).commit();
        }else if (object instanceof Boolean){
            sp.edit().putBoolean(name, (Boolean) object).commit();
        }
    }
    public String getString(String name){
        return sp.getString(name,"");
    }
    public boolean getBoolean(String name){
        return sp.getBoolean(name,false);
    }
}
