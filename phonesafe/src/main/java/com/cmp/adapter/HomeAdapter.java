package com.cmp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmp.data.HomeItem;
import com.cmp.myapplication.R;

import java.util.List;

/**
 * 作者：cmp on 2016/9/9 18:00
 */
public class HomeAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<HomeItem> mDataList;

    public HomeAdapter(Context context, List<HomeItem> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = list;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.activity_home_item, null);
            holder.home_icon = (ImageView) convertView.findViewById(R.id.home_item_icon);
            holder.home_name = (TextView) convertView.findViewById(R.id.home_item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HomeItem home = mDataList.get(position);
        holder.home_icon.setImageResource(home.itemImg);
        holder.home_name.setText(home.itemName);
        return convertView;
    }
    class ViewHolder {
        public ImageView home_icon;
        public TextView home_name;
    }
}
