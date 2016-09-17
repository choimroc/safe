package com.cmp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cmp.adapter.AppPageAdapter;
import com.cmp.data.AppInfo;
import com.cmp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AppManagerActivity extends Activity {
    private TabLayout mTabLayout;//选项卡标题布局
    private ViewPager mViewPager;//选项卡内容布局
    private ArrayList<View> mArrayList = new ArrayList<>();//viewpager布局
    private String[] mTitle;//选项卡标题
    private TextView mPhoneMemory;
    private TextView mSDMemory;
    private List<AppInfo> appInfos;
    private List<AppInfo> userAppInfos=new ArrayList<>();
    private List<AppInfo> systemAppInfos=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initData();
        initView();
        initAdapter();
    }

    //返回按钮
    public void btn_back(View v) {
        finish();
    }

    //初始化组件
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.app_tab);
        mViewPager = (ViewPager) findViewById(R.id.app_vp);
        LayoutInflater li = LayoutInflater.from(this);
        View view1 = li.inflate(R.layout.activity_app_page1, null);
        View view2 = li.inflate(R.layout.activity_app_page2, null);
        View view3 = li.inflate(R.layout.activity_app_page3, null);
        mArrayList.add(view1);
        mArrayList.add(view2);
        mArrayList.add(view3);
    }

    //适配器
    private void initAdapter() {
        AppPageAdapter mAdapter = new AppPageAdapter(mArrayList, mTitle, AppManagerActivity.this);
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    public void initData() {
        mTitle = super.getResources().getStringArray(R.array.soft_tab);
    }
}
