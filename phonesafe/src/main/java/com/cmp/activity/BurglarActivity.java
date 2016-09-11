package com.cmp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cmp.adapter.BurglarAdapter;
import com.cmp.myapplication.R;

/**
 * 作者：cmp on 2016/9/9 23:20
 */
public class BurglarActivity extends Activity {
    private GridView burglar_gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burglar);
        initView();
    }

    //初始化GridView
    private void initView() {
        burglar_gv = (GridView) findViewById(R.id.burglar_gv);
        burglar_gv.setAdapter(new BurglarAdapter(BurglarActivity.this));
        burglar_gv.setOnItemClickListener(BurglarOnClick);
        burglar_gv.setSelector(new ColorDrawable(Color.TRANSPARENT));//取消点击背景色
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
