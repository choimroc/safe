package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cmp.adapter.HomeAdapter;
import com.cmp.myapplication.R;

/**
 * 作者：cmp on 2016/9/9 17:06
 */
public class HomeActivity extends Activity {
    private ImageView roteView;
    private GridView home_gv;
    private long mExitTime;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        roteView = (ImageView) findViewById(R.id.rote_icon);
        startRotate();
        initView();
    }

    //旋转动画
    private void startRotate() {
        Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotating_forever);
        roteView.setAnimation(rotateAnim);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnim.setInterpolator(lir);
        rotateAnim.start();
    }

    //初始化GridView
    private void initView() {
        home_gv = (GridView) findViewById(R.id.home_gv);
        home_gv.setAdapter(new HomeAdapter(HomeActivity.this));
        home_gv.setOnItemClickListener(homeOnClick);
        home_gv.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消点击背景色
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

    private AdapterView.OnItemClickListener homeOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    preferences = getSharedPreferences("mydata", MODE_WORLD_READABLE);
                    String burglar = preferences.getString("burglar", "");
                    Log.i("tag", burglar + "");
                    if (burglar.length() == 0) {
                        startActivity(SetPWActivity.class);
                    } else {
                        startActivity(EditPWActivity.class);
                    }
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

