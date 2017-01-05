package com.example.dongpeng.havenoname.my;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.entity.Person;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class MyFragment extends Fragment implements View.OnClickListener{
    private TextView tv_register;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_lay,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_register= (TextView) view.findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivityForResult(intent,1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Person person= (Person) data.getSerializableExtra("person");
        Log.e("------------",requestCode+"");
    }
}
