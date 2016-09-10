package com.cmp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmp.myapplication.R;

import org.w3c.dom.Text;

/**
 * 作者：cmp on 2016/9/9 18:00
 */
public class HomeAdapter extends BaseAdapter {
    int[] imageId = {R.mipmap.home_burglar, R.mipmap.home_address, R.mipmap.home_application,
            R.mipmap.home_kill, R.mipmap.home_clear, R.mipmap.home_process,
            R.mipmap.home_flow, R.mipmap.home_tool, R.mipmap.home_setting
    };
    String[] names = {
            "手机防盗", "通讯卫士", "软件管家", "手机杀毒", "清理缓存",
            "进程管理", "流量统计", "高级工具", "设置中心"
    };
    private Context context;

    public HomeAdapter(Context context) {
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
        View view = View.inflate(context,R.layout.home_item,null);
        ImageView home_icon=(ImageView)view.findViewById(R.id.home_item_icon);
        TextView home_name=(TextView)view.findViewById(R.id.home_item_name);
        home_icon.setImageResource(imageId[position]);
        home_name.setText(names[position]);
        return view;
    }
}
