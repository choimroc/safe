package com.cmp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmp.myapplication.R;

/**
 * 作者：cmp on 2016/9/9 18:00
 */
public class BurglarAdapter extends BaseAdapter {
    int[] imageId = {
            R.mipmap.burglar_gps, R.mipmap.burglar_lock, R.mipmap.burglar_music,
            R.mipmap.burglar_contact, R.mipmap.burglar_delete, R.mipmap.burglar_search
    };
    String[] names = {
            "手机定位", "手机锁定", "响铃警报", "紧急联系人", "远程删除数据", "帮助他人找手机"
    };
    private Context context;

    public BurglarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageId.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = View.inflate(context, R.layout.burglar_item, null);
        ImageView home_icon = (ImageView) view.findViewById(R.id.burglar_item_icon);
        TextView home_name = (TextView) view.findViewById(R.id.burglar_item_name);
        home_icon.setImageResource(imageId[position]);
        home_name.setText(names[position]);
        return view;
    }
}
