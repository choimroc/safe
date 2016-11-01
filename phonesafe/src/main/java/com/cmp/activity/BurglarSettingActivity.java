package com.cmp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cmp.phonesafe.R;


/**
 * 作者：cmp on 2016/9/11 23:32
 * <p>
 * 手机防盗设置
 */
public class BurglarSettingActivity extends Activity implements View.OnClickListener {
    private TextView mToolbarTitleTv;
    private ImageButton mToolbarBackBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar_setting);
        initView();
    }

    private void initView() {
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mToolbarTitleTv.setText(R.string.activity_burglar_setting);
        mToolbarBackBtn = (ImageButton) findViewById(R.id.toolbar_back_btn);
        mToolbarBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_btn:
                finish();
                break;
        }
    }
}
