package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cmp.adapter.CommonAdapter;
import com.cmp.adapter.ViewHolder;
import com.cmp.data.BurglarItem;
import com.cmp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：cmp on 2016/9/9 23:20
 */
public class BurglarActivity extends Activity {
    private List<BurglarItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar);
        initData();
        initView();
    }

    //初始化GridView
    private void initView() {
        GridView burglar_gv = (GridView) findViewById(R.id.burglar_gv);
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

    //初始化数据
    private void initData() {
        dataList = new ArrayList<>();
        TypedArray getImageId = super.getResources().obtainTypedArray(R.array.burglar_item);
        int[] imageId = new int[getImageId.length()];
        for (int j = 0; j < getImageId.length(); j++) {
            imageId[j] = getImageId.getResourceId(j, 0);
        }
        String[] names = super.getResources().getStringArray(R.array.burglar_name);
        for (int i = 0; i < getImageId.length(); i++) {
            dataList.add(new BurglarItem(imageId[i], names[i]));
        }
        getImageId.recycle();
    }

    //开启新的Activity不关闭自己
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(BurglarActivity.this, cls);
        startActivity(intent);
    }

    //返回按钮
    public void btn_back(View v) {
        finish();
    }

    //进入设置
    public void btn_setting(View v) {
        Intent intent = new Intent(BurglarActivity.this, BurglarSettingActivity.class);
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
}
