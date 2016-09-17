package com.cmp.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 作者：cmp on 2016/9/16 14:56
 */
public class AppPageAdapter extends PagerAdapter {
    private ArrayList<View> mArrayList;
    private String[] mTitle;
    private Context mContext;

    public AppPageAdapter(ArrayList<View> mArrayList, String[] mTitle, Context mContext) {
        super();
        this.mTitle=mTitle;
        this.mArrayList = mArrayList;
        this.mContext = mContext;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mArrayList.get(position), 0);
        return mArrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mArrayList.get(position));
    }
}
