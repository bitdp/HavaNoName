package com.example.dongpeng.havenoname;

import android.os.Bundle;
import android.widget.Button;

import com.example.dongpeng.havenoname.base.BaseActivity;
import com.example.dongpeng.havenoname.entity.Person;
import com.example.dongpeng.havenoname.utils.ToastUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BmobQuery<Person> query = new BmobQuery<Person>();
//                //查询playerName叫“比目”的数据
//                query.addWhereEqualTo("name", "lucky");
//                //返回50条数据，如果不加上这条语句，默认返回10条数据
//                query.setLimit(1);
//                //执行查询方法
//                query.findObjects(new FindListener<Person>() {
//                    @Override
//                    public void done(List<Person> object, BmobException e) {
//                        if (e == null) {
//                            showToast("查询成功：共" + object.size() + "条数据。");
//                        } else {
//                            showToast("失败：" + e.getMessage() );
//                        }
//                    }
//                });
//            }
//        });
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    ToastUtil.show(MainActivity.this,"添加数据成功，返回objectId为："+objectId);
                }else{
                    ToastUtil.show(MainActivity.this,"创建数据失败：" + e.getMessage());
                }
            }
        });
    }
}
