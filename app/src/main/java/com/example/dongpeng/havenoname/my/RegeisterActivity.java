package com.example.dongpeng.havenoname.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.base.BaseActivity;
import com.example.dongpeng.havenoname.entity.Person;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class RegeisterActivity extends BaseActivity {
    private EditText et_name, et_pass;
    private Button bt_login;
    private TextView tv_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lay);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);
        bt_login = (Button) findViewById(R.id.button);
        bt_login.setText("注册");
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String pass = et_pass.getText().toString();
                Person p2=new Person();
                p2.setName(name);
                p2.setPassword(pass);
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if (e == null) {
                            showToast("注册成功");
                            setResult(1,new Intent().putExtra("objectId",objectId));
                            finish();
                        } else {
                            showToast("注册失败");
                        }
                    }
                });
            }
        });
        tv_reg = (TextView) findViewById(R.id.tv_reg);
        tv_reg.setVisibility(View.GONE);
    }
}
