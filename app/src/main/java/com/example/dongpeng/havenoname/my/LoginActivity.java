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

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_name,et_pass;
    private Button bt_login;
    private TextView tv_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lay);
        initView();
    }

    private void initView() {
        et_name= (EditText) findViewById(R.id.et_name);
        et_pass= (EditText) findViewById(R.id.et_pass);
        bt_login= (Button) findViewById(R.id.button);
        bt_login.setOnClickListener(this);
        tv_reg= (TextView) findViewById(R.id.tv_reg);
        tv_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                BmobQuery<Person> query = new BmobQuery<Person>();
                //查询playerName叫“比目”的数据
                query.addWhereEqualTo("name", name);
                query.addWhereEqualTo("password",pass);
                //返回50条数据，如果不加上这条语句，默认返回10条数据
                query.setLimit(1);
                //执行查询方法
                query.findObjects(new FindListener<Person>() {
                    @Override
                    public void done(List<Person> object, BmobException e) {
                        if (e == null&&!object.isEmpty()) {
                            showToast("登录成功");
                            setResult(RESULT_OK,new Intent().putExtra("person",object.get(0)));
                            finish();
                        } else {
                            showToast("用户名或密码错误");
                        }
                    }
                });
            break;
            case R.id.tv_reg:
                Intent intent=new Intent(this,RegeisterActivity.class);
                startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==1){
            showToast("登录成功");
        }
    }
}
