package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmp.myapplication.R;
import com.cmp.util.MD5Util;

/**
 * 作者：cmp on 2016/9/10 01:13
 */
public class EditPWActivity extends Activity {
    private EditText mEdit;
    private Button mBtn;
    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpw);
        initView();
    }

    private void initView() {
        mEdit = (EditText) findViewById(R.id.edit_pw);
        mBtn = (Button) findViewById(R.id.btn_true);
        mEdit.addTextChangedListener(mTextWatcher);

    }
    //返回按钮
    public void btn_back(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    public void check(View v) {
        //提取设置好的密码
        preferences = getSharedPreferences("mydata", MODE_WORLD_READABLE);
        String burglar = preferences.getString("burglar", "");
        if (MD5Util.md5(mEdit.getText().toString()).equals(burglar))// 判断密码是否一致
        {
            //跳转
            Intent intent = new Intent();
            intent.setClass(this, BurglarActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(this, "两次输入不一致，请重新设置", Toast.LENGTH_SHORT).show();
        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

        }

        //控制登录按钮
        @Override
        public void afterTextChanged(Editable s) {
            editStart = mEdit.getSelectionStart();
            editEnd = mEdit.getSelectionEnd();
            if (temp.length() > 0) {
                mBtn.setBackgroundResource(R.drawable.click_true);
                mBtn.setEnabled(true);
            } else {
                mBtn.setBackgroundResource(R.drawable.click_false);
                mBtn.setEnabled(false);
            }
        }
    };
}
