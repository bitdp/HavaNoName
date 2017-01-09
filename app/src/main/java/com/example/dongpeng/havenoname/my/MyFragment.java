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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.entity.Person;
import com.example.dongpeng.havenoname.interfac.ProgressListener;
import com.example.dongpeng.havenoname.utils.DensityUtil;
import com.example.dongpeng.havenoname.utils.LogUtil;
import com.example.dongpeng.havenoname.utils.SaveFileUtil;
import com.example.dongpeng.havenoname.utils.httputil.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dongpeng on 2017/1/4.
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    List<String> list=new ArrayList<>();
    private boolean isCreated=false;
    private String fileUrl="http://192.168.12.30:8080/aaa.pdf";
    private TextView tv_register,
                      tv_update;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_lay, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        LogUtil.d(list.toString());
        tv_register = (TextView) view.findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        tv_update= (TextView) view.findViewById(R.id.tv_update);
        Drawable drawable = ContextCompat.getDrawable(getActivity(),R.mipmap.update);
        drawable.setBounds(0, 0, DensityUtil.dp2px(getActivity(),20), DensityUtil.dp2px(getActivity(),20));//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        tv_update.setCompoundDrawables(drawable, null, null, null);//只放左边
        tv_update.setOnClickListener(this);
        if (!isCreated){
            loadData();
        }
    }

    private void loadData() {
        LogUtil.d("=======loadData======");
        list.add("999");
        list.add("888");
        isCreated=true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_update:
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.update_dialog,null);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(DensityUtil.dp2px(getActivity(),200), ViewGroup.LayoutParams.WRAP_CONTENT);
                final Dialog dialog=new Dialog(getActivity(), R.style.customDialog);
                dialog.setContentView(view,params);
                dialog.setCanceledOnTouchOutside(false);
                view.findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        downLoadFile(dialog);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void downLoadFile(final Dialog dialog) {
        HttpUtils.createService(new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                Log.e("-----",bytesRead+"-----"+contentLength+"------"+done);
            }
        }).downloadAPK(fileUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                new SaveFileUtil().saveFile(getActivity(),response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Person person = (Person) data.getSerializableExtra("person");
        Log.e("------------", requestCode + "");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.d("======onHiddenChanged=======");
    }
}
