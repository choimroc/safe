package com.cmp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmp.phonesafe.R;


/**
 * 作者：cmp on 2016/9/11 23:32
 * <p>
 * 手机防盗设置
 */
public class BurglarSettingActivity extends Activity {
    private TextView mToolbarTitleTv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar_setting);
        initView();
    }

    //返回按钮
    public void btnBack(View v) {
        finish();
    }

    private void initView() {
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mToolbarTitleTv.setText(R.string.activity_burglar_setting);
    }
}
