package com.example.dongpeng.havenoname.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.utils.ToastUtil;
import com.example.dongpeng.havenoname.view.ShopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongpeng on 2017/1/17.
 */

public class HomeFragment extends Fragment {
    private ShopView sv;
    private List<String> data = new ArrayList<>();
    private ViewFlipper vf;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_lay, container, false);
        sv= (ShopView) view.findViewById(R.id.sv);
        initData();
        vf = (ViewFlipper) view.findViewById(R.id.vf);
        if (data.size() % 2 == 1) {//数据源为偶数
            data.add("");
        }
        addData();
        vf.startFlipping();
        return view;
    }

    private void addData() {
        for (int i = 0; i < data.size() / 2; i++) {
            View v = View.inflate(getActivity(), R.layout.flipper_item, null);
            TextView tv1 = (TextView) v.findViewById(R.id.tv1);
            TextView tv2 = (TextView) v.findViewById(R.id.tv2);
            tv1.setText(data.get(2 * i));
            tv2.setText(data.get(2 * i + 1));
            final int finalI = i;
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(getActivity(), data.get(2*finalI));
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(getActivity(), data.get(2*finalI + 1));
                }
            });
            vf.addView(v);
        }
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            data.add("这是第" + i + "条数据");
        }
    }
}
