package com.cmp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cmp.myapplication.R;

/**
 * 作者：cmp on 2016/9/11 23:32
 */
public class BurglarSettingActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar_setting);
    }
    //返回按钮
    public void btn_back(View v) {
        finish();
    }
}
