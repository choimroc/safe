package com.cmp.adapter;

import android.content.Context;

import com.cmp.data.AppInfo;
import com.cmp.phonesafe.R;
import com.cmp.util.FileSizeUtil;

import java.util.List;

/**
 * 作者：cmp on 2016/9/20 14:10
 */
public class AppListViewAdapter2 extends CommonAdapter<AppInfo> {
    private Context context;
    private List<AppInfo> mData;
    private int itemLayoutId;

    public AppListViewAdapter2(Context context, List<AppInfo> mData, int itemLayoutId) {
        super(context, mData, itemLayoutId);
        this.context = context;
        this.mData = mData;
        this.itemLayoutId = itemLayoutId;

    }

    @Override
    public void convert(ViewHolder helper, AppInfo item) {
        helper.setImageDrawable(R.id.app_icon, item.getIcon());
        helper.setText(R.id.app_name, item.getName());
        helper.setText(R.id.app_size, FileSizeUtil.formatFileSize(item.getAppSize()));
        helper.setText(R.id.app_date, item.getAppDate());
        helper.setText(R.id.app_version, context.getString(R.string.activity_app_version) + item.getAppVersion());
    }
}
