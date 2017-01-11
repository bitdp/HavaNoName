package com.example.dongpeng.havenoname.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dongpeng.havenoname.R;

import java.util.List;

/**
 * Created by dongpeng on 2017/1/10.
 */

public class MyOptionAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    public MyOptionAdapter(Context context, List<String>data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null){
            holder=new MyViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.my_item,null);
            convertView.setTag(holder);
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.textView= (TextView) convertView.findViewById(R.id.tv_update);
        holder.textView.setText(data.get(position));
        return convertView;
    }
    class MyViewHolder {
        TextView textView;
    }
}
