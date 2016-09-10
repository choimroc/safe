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
import android.widget.Toast;

import com.cmp.myapplication.R;
import com.cmp.util.MD5Util;

/**
 * 作者：cmp on 2016/9/9 20:49
 */
public class SetPWActivity extends Activity {
    private EditText mEdit1;
    private EditText mEdit2;
    private String mPassWord;
    private Button mBtn;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpw);
        initView();
    }

    //返回按钮
    public void btn_back(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        mEdit1 = (EditText) findViewById(R.id.editText1);
        mEdit2 = (EditText) findViewById(R.id.editText2);
        mBtn = (Button) findViewById(R.id.btn_true);
        mEdit2.addTextChangedListener(mTextWatcher);
    }

    public void check(View v) {
        mPassWord = mEdit1.getText().toString();//获取设置的密码
        if (mEdit1.equals(mEdit2.getText().toString())) // 判断密码是否一致
        {

            //记录已经设置密码
            preferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            editor = preferences.edit();
            //记录加密的密码
            editor.putString("buglar", MD5Util.md5(mPassWord));
            editor.commit();
            //跳转
            if (editor.commit()) {
                Intent intent = new Intent();
                intent.setClass(this, BurglarActivity.class);
                startActivity(intent);
            }
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
            editStart = mEdit2.getSelectionStart();
            editEnd = mEdit2.getSelectionEnd();
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
