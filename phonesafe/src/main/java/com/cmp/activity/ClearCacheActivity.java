package com.cmp.activity;

import android.app.Activity;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cmp.adapter.CacheListViewAdapter;
import com.cmp.data.AppInfo;
import com.cmp.phonesafe.R;
import com.cmp.util.AppInfoParser;
import com.cmp.util.FileSizeUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClearCacheActivity extends Activity implements View.OnClickListener {
    protected static final int SCANNING = 101;
    protected static final int SCAN_FINISH = 102;
    private TextView mToolbarTitleTv;
    private TextView mCacheSizeAllTv;
    private TextView mCacheScanningTv;
    private List<AppInfo> appInfos;//总集合
    private List<AppInfo> hasCacheInfo;//有缓存的
    private CacheListViewAdapter mCacheListViewAdapter;
    private ListView mCacheScannerLv;
    private Button mCacheStartBtn;
    private long ccheSize;
    private int process;
    private boolean isClear;
    private ProgressBar mCacheScannerProgressBar;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCANNING://正在扫描
                    AppInfo info = (AppInfo) msg.obj;
                    mCacheScanningTv.setText(getString(R.string.activity_ant_scanning) + info.getName());
                    ccheSize += info.getAppCacheSize();
                    mCacheSizeAllTv.setText(FileSizeUtil.formatFileSize(ccheSize));
                    if (info.getAppCacheSize() != 0) {
                        hasCacheInfo.add(info);
                    }
                    //进度
                    process++;
                    mCacheScannerProgressBar.setProgress(process / msg.arg1 * 100);
                    mCacheListViewAdapter.notifyDataSetChanged();
                    mCacheScannerLv.setSelection(hasCacheInfo.size());
                    mCacheStartBtn.setText(R.string.activity_ant_stop);
                    break;
                case SCAN_FINISH://扫描完成
                    mCacheScanningTv.setText(R.string.activity_ant_scan_finish);
                    mCacheStartBtn.setText(getString(R.string.activity_cache_clear) + "（" + FileSizeUtil.formatFileSize(ccheSize) + "）");
                    isClear = true;
                    break;
            }
        }
    };
    private ImageButton mToolbarBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clearcache);
        initView();
        initData();
        initCacheData();
        mCacheScannerLv.setAdapter(mCacheListViewAdapter);
    }


    private void initView() {
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mCacheSizeAllTv = (TextView) findViewById(R.id.cache_size_all_tv);
        mCacheScanningTv = (TextView) findViewById(R.id.cache_scanning_tv);
        mCacheStartBtn = (Button) findViewById(R.id.cache_start_btn);
        mCacheStartBtn.setOnClickListener(this);
        mCacheScannerLv = (ListView) findViewById(R.id.cache_scanner_lv);
        mCacheScannerProgressBar = (ProgressBar) findViewById(R.id.cache_scanner_progressBar);
        mToolbarBackBtn = (ImageButton) findViewById(R.id.toolbar_back_btn);
        mToolbarBackBtn.setOnClickListener(this);
    }

    private void initData() {
        hasCacheInfo = new ArrayList<>();
        appInfos = new ArrayList<>();
        mToolbarTitleTv.setText(getResources().getStringArray(R.array.home_name)[4]);
        mCacheListViewAdapter = new CacheListViewAdapter(getApplicationContext(),
                hasCacheInfo, R.layout.activity_cache_item);
    }

    class ClearCacheObserver extends IPackageDataObserver.Stub {
        public void onRemoveCompleted(final String packageName, final boolean succeeded) {

        }
    }

    private void clear() {
        Method[] methods = PackageManager.class.getMethods();
        for (Method method : methods) {
            if ("freeStorageAndNotify".equals(method.getName())) {
                try {
                    method.invoke(getPackageManager(), Long.MAX_VALUE, new ClearCacheObserver());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void initCacheData() {
        process = 0;
        new Thread() {
            public void run() {
                appInfos.clear();
                hasCacheInfo.clear();
                appInfos.addAll(AppInfoParser.getappInfos(ClearCacheActivity.this));
                //判断是个人软件还是系统软件
                for (AppInfo appInfo : appInfos) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.obj = appInfo;
                    msg.what = SCANNING;
                    msg.arg1 = appInfos.size();
                    mHandler.sendMessage(msg);
                }
                Message msg = Message.obtain();
                msg.what = SCAN_FINISH;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cache_start_btn:
                if (isClear) {
                    clear();
                }
                break;
            case R.id.toolbar_back_btn:
                finish();
                break;
        }
    }
}
