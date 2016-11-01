package com.cmp.adapter;

import android.content.Context;

import com.cmp.data.AppInfo;
import com.cmp.phonesafe.R;
import com.cmp.util.FileSizeUtil;

import java.util.List;

/**
 * 作者：cmp on 2016/9/20 13:59
 */
public class CacheListViewAdapter extends CommonAdapter<AppInfo> {
    private Context context;
    private List<AppInfo> mData;
    private int itemLayoutId;

    public CacheListViewAdapter(Context context, List<AppInfo> mData, int itemLayoutId) {
        super(context, mData, itemLayoutId);
        this.context = context;
        this.mData = mData;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public void convert(ViewHolder helper, AppInfo item) {
        helper.setText(R.id.cache_app_name, item.getName());
        helper.setText(R.id.cache_cache_size, FileSizeUtil.formatFileSize(item.getAppCacheSize()));
        helper.setImageDrawable(R.id.cache_app_icon, item.getIcon());
    }
}

