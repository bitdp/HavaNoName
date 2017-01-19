package com.example.dongpeng.havenoname.tantan;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dongpeng.havenoname.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongpeng on 2017/1/17.
 */

public class TanTanFragment extends Fragment {
    private SwipeRefreshLayout spl;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            spl.setRefreshing(false);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refresh_lay,container,false);
        ListView listView= (ListView) view.findViewById(R.id.lv);
        spl= (SwipeRefreshLayout) view.findViewById(R.id.spl);
        spl.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_green_light));
        spl.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorWhite);
        List<String> data=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("这是第"+i+"条数据");
        }
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data));
        spl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(1,3000);
            }
        });
        return view;
    }
}
