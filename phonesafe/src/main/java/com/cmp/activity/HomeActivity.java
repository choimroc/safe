package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cmp.adapter.CommonAdapter;
import com.cmp.adapter.ViewHolder;
import com.cmp.data.HomeItem;
import com.cmp.phonesafe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：cmp on 2016/9/9 17:06
 * <p>
 * 首页
 */
public class HomeActivity extends Activity {
    private ImageView roteView;//旋转
    private long mExitTime;
    private List<HomeItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initView();
        startRotate();
    }

    //旋转动画
    private void startRotate() {
        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotating_forever);
        roteView.setAnimation(rotateAnim);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnim.setInterpolator(lir);
        rotateAnim.start();
    }

    //初始化页面
    private void initView() {
        roteView = (ImageView) findViewById(R.id.home_rote_icon);
        //初始化GridView
        GridView home_gv = (GridView) findViewById(R.id.home_gv);
        home_gv.setAdapter(new CommonAdapter<HomeItem>(getApplicationContext(),
                dataList, R.layout.activity_home_item) {
            @Override
            public void convert(ViewHolder viewHolder, HomeItem item) {
                viewHolder.setImageResource(R.id.home_item_icon, item.getItemImg());
                viewHolder.setText(R.id.home_item_name, item.getItemName());
            }
        });
        home_gv.setOnItemClickListener(homeOnClick);
    }

    //初始化数据
    private void initData() {
        dataList = new ArrayList<>();
        //获取item的图标
        TypedArray getImageId = super.getResources().obtainTypedArray(R.array.home_item);
        int[] imageId = new int[getImageId.length()];
            for (int j = 0; j < getImageId.length(); j++) {
                imageId[j] = getImageId.getResourceId(j, 0);
        }
        //获取item的名称
        String[] names = super.getResources().getStringArray(R.array.home_name);
        for (int i = 0; i < getImageId.length(); i++) {
            dataList.add(new HomeItem(imageId[i], names[i]));
        }
        getImageId.recycle();
    }

    //开启新的Activity不关闭自己
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        startActivity(intent);
    }

    //按两次返回键退出程序
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //对item进行点击监听
    private AdapterView.OnItemClickListener homeOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    SharedPreferences preferences = getSharedPreferences("MyData", 2);
                    String burglar = preferences.getString("burglar", "");
                    if (burglar.length() == 0) {
                        startActivity(SetPasswordActivity.class);
                    } else {
                        startActivity(EditPasswordActivity.class);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    startActivity(AppManagerActivity.class);
                    break;
                case 3:
                    startActivity(AntivirusActivity.class);
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
            }
        }
    };
}

