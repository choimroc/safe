package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cmp.adapter.CommonAdapter;
import com.cmp.adapter.ViewHolder;
import com.cmp.data.BurglarItem;
import com.cmp.phonesafe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：cmp on 2016/9/9 23:20
 * <p>
 * 手机防盗
 */
public class BurglarActivity extends Activity implements View.OnClickListener {
    private List<BurglarItem> dataList;
    private TextView mToolbarTitleTv;
    private GridView burglar_gv;
    private ImageButton mToolbarSettingBtn;
    private ImageButton mToolbarBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar);
        initView();
        initData();

    }

    //初始化GridView
    private void initView() {
        burglar_gv = (GridView) findViewById(R.id.burglar_gv);
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mToolbarSettingBtn = (ImageButton) findViewById(R.id.toolbar_setting_btn);
        mToolbarSettingBtn.setOnClickListener(this);
        mToolbarBackBtn = (ImageButton) findViewById(R.id.toolbar_back_btn);
        mToolbarBackBtn.setOnClickListener(this);
    }


    //初始化数据
    private void initData() {
        //设置标题
        mToolbarTitleTv.setText(getResources().getStringArray(R.array.home_name)[0]);
        dataList = new ArrayList<>();
        //获取item图标
        TypedArray getImageId = super.getResources().obtainTypedArray(R.array.burglar_item);
        int[] imageId = new int[getImageId.length()];
        for (int j = 0; j < getImageId.length(); j++) {
            imageId[j] = getImageId.getResourceId(j, 0);
        }
        //获取item名称
        String[] names = getResources().getStringArray(R.array.burglar_name);
        for (int i = 0; i < getImageId.length(); i++) {
            dataList.add(new BurglarItem(imageId[i], names[i]));
        }
        getImageId.recycle();
        burglar_gv.setAdapter(new CommonAdapter<BurglarItem>(getApplicationContext(),
                dataList, R.layout.activity_burglar_item) {
            @Override
            public void convert(ViewHolder viewHolder, BurglarItem item) {
                viewHolder.setImageResource(R.id.burglar_item_icon, item.getItemImg());
                viewHolder.setText(R.id.burglar_item_name, item.getItemName());
            }
        });
        burglar_gv.setOnItemClickListener(BurglarOnClick);
    }

    //开启新的Activity不关闭自己
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(BurglarActivity.this, cls);
        startActivity(intent);
    }


    private AdapterView.OnItemClickListener BurglarOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_setting_btn:    //进入设置
                Intent intent = new Intent(BurglarActivity.this, BurglarSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_back_btn:
                finish();
                break;
        }
    }
}
