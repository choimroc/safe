package com.cmp.adapter;

import android.content.Context;

import com.cmp.data.ScanAppInfo;
import com.cmp.phonesafe.R;

import java.util.List;

/**
 * 作者：cmp on 2016/10/13 18:51
 */
public class AntListViewAdapter extends CommonAdapter<ScanAppInfo> {
    private Context context;
    private List<ScanAppInfo> mData;
    private int itemLayoutId;

    public AntListViewAdapter(Context context, List mData, int itemLayoutId) {
        super(context, mData, itemLayoutId);
        this.context = context;
        this.mData = mData;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public void convert(ViewHolder holder, ScanAppInfo item) {
        holder.setImageDrawable(R.id.ant_app_icon, item.getAppicon());
        holder.setText(R.id.ant_app_name, item.getAppName());
        if (!item.isVirus()) {
            holder.setText(R.id.ant_issafe, context.getResources().getString(R.string.activity_ant_safe));
        }else {
            holder.setText(R.id.ant_issafe, context.getResources().getString(R.string.activity_ant_safe));
        }

    }
}
