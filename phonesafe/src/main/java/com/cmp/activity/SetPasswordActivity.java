package com.cmp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cmp.phonesafe.R;
import com.cmp.util.MD5Util;

/**
 * 作者：cmp on 2016/9/9 20:49
 * <p>
 * 手机防盗设置密码
 */
public class SetPasswordActivity extends Activity implements View.OnClickListener {
    private EditText mEdit1;
    private EditText mEdit2;
    private Button mBtn;
    private TextView mToolbarTitleTv;
    private ImageButton mToolbarBackBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpw);
        initView();
    }


    private void initView() {
        mEdit1 = (EditText) findViewById(R.id.editText1);
        mEdit2 = (EditText) findViewById(R.id.editText2);
        mBtn = (Button) findViewById(R.id.btn_true);
        mEdit2.addTextChangedListener(mTextWatcher);
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mToolbarTitleTv.setText(R.string.activity_setpw_title);
        mToolbarBackBtn = (ImageButton) findViewById(R.id.toolbar_back_btn);
        mToolbarBackBtn.setOnClickListener(this);
    }

    public void check(View v) {
        String mPassWord = mEdit1.getText().toString();
        if (mPassWord.equals(mEdit2.getText().toString())) // 判断密码是否一致
        {
            //记录已经设置密码
            SharedPreferences preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //记录加密的密码
            editor.putString("burglar", MD5Util.md5(mPassWord));
            editor.commit();
            //跳转
            if (editor.commit()) {
                Intent intent = new Intent();
                intent.setClass(SetPasswordActivity.this, BurglarActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(SetPasswordActivity.this, "两次输入不一致，请重新设置", Toast.LENGTH_SHORT).show();
        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

        }

        //控制确定按钮
        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() > 0) {
                mBtn.setBackgroundResource(R.drawable.click_true);
                mBtn.setEnabled(true);
            } else {
                mBtn.setBackgroundResource(R.drawable.click_false);
                mBtn.setEnabled(false);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_btn:
                finish();
                break;
        }
    }
}
