package com.example.dongpeng.havenoname.my;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.entity.Person;
import com.example.dongpeng.havenoname.utils.DensityUtil;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    private TextView tv_register,
                      tv_update;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_lay, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_register = (TextView) view.findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        tv_update= (TextView) view.findViewById(R.id.tv_update);
        Drawable drawable = ContextCompat.getDrawable(getActivity(),R.mipmap.update);
        drawable.setBounds(0, 0, DensityUtil.dp2px(getActivity(),20), DensityUtil.dp2px(getActivity(),20));//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        tv_update.setCompoundDrawables(drawable, null, null, null);//只放左边
        tv_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_update:
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.login_lay);
                dialog.setCancelable(false);
                dialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Person person = (Person) data.getSerializableExtra("person");
        Log.e("------------", requestCode + "");
    }
}
