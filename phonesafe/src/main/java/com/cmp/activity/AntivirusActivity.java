package com.cmp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmp.adapter.AntListViewAdapter;
import com.cmp.dao.AntiVirusDao;
import com.cmp.data.ScanAppInfo;
import com.cmp.phonesafe.R;
import com.cmp.util.CopyFileFromAssets;
import com.cmp.util.DensityUtil;
import com.cmp.util.MD5Util;
import com.cmp.view.RadarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * 作者：cmp on 2016/10/12 11:11
 * <p>
 * 手机杀毒
 */
public class AntivirusActivity extends Activity implements View.OnClickListener {
    protected static final int SCAN_BENGIN = 100;
    protected static final int SCANNING = 101;
    protected static final int SCAN_FINISH = 102;
    private RadarView mAntRadarView;//扫描图
    private TextView mAntScannerTv;//显示当前扫描状态
    private int total;
    private int process;
    private ListView mAntScannerLv;//扫描程序列表
    private AntListViewAdapter mAntListViewAdapter;//适配器
    private Button mAntStartBtn;//开始扫描按钮
    private boolean flag;
    private boolean isStop;
    private TextView mProcessTv;//百分比进度
    private List<ScanAppInfo> mScanAppInfos = new ArrayList<>();
    private PackageManager pm;
    private ImageButton mBack;//返回按钮
    private SharedPreferences preferences;
    private Animation rotateAnim;//旋转动画

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCAN_BENGIN://初始化杀毒引擎
                    mAntScannerTv.setText(R.string.activity_ant_scan_init);
                    break;
                case SCANNING://正在扫描
                    ScanAppInfo info = (ScanAppInfo) msg.obj;
                    mAntScannerTv.setText(getString(R.string.activity_ant_scanning) + info.getAppName());
                    int speed = msg.arg1;
                    mProcessTv.setText((speed * 100 / total) + "%");
                    mScanAppInfos.add(info);
                    mAntListViewAdapter.notifyDataSetChanged();
                    mAntScannerLv.setSelection(mScanAppInfos.size());
                    mAntStartBtn.setText(R.string.activity_ant_stop);
                    break;
                case SCAN_FINISH://扫描完成
                    mAntScanImg.clearAnimation();//清除动画
                    mAntScannerTv.setText(R.string.activity_ant_scan_finish);
                    mAntStartBtn.setText(R.string.activity_ant_start);
                    saveScanTime();
                    break;
            }
        }

        //记录上次查杀时间
        private void saveScanTime() {
            //格式化时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentTime = sdf.format(new Date());
            //记录上次查杀时间到本地
            preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("lastVirusScan", currentTime);
            editor.apply();
        }
    };
    private TextView mToolbarTitleTv;
    private ImageView mAntScanImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antivirus);
        copyDB();
        initView();
        initData();
        startRotate();
    }

    //初始化组件
    private void initView() {
        mAntRadarView = (RadarView) findViewById(R.id.ant_RadarView);
        mAntScannerTv = (TextView) findViewById(R.id.ant_scanner_tv);
        mAntScannerLv = (ListView) findViewById(R.id.ant_scanner_lv);
        mAntStartBtn = (Button) findViewById(R.id.ant_start_btn);
        mProcessTv = (TextView) findViewById(R.id.ant_process_tv);
        mBack = (ImageButton) findViewById(R.id.back);
        mToolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        mAntScanImg = (ImageView) findViewById(R.id.ant_scan_img);
    }

    //初始化数据
    private void initData() {
        mToolbarTitleTv.setText(getResources().getStringArray(R.array.home_name)[3]);
        pm = getApplicationContext().getPackageManager();
        preferences = getSharedPreferences("MyData", 2);
        String lastTime = preferences.getString("lastVirusScan", "");
        if (lastTime.equals("")) {
            mAntScannerTv.setText(R.string.activity_ant_never);
        } else {
            mAntScannerTv.setText(getString(R.string.activity_ant_last) + lastTime);
        }
        mAntListViewAdapter = new AntListViewAdapter
                (getApplicationContext(), mScanAppInfos, R.layout.activity_antivirus_item);
        mAntScannerLv.setAdapter(mAntListViewAdapter);
        mAntStartBtn.setOnClickListener(this);
    }

    /**
     * 拷贝病毒数据库
     */
    private void copyDB() {
        new Thread() {
            public void run() {
                CopyFileFromAssets.copy(getApplicationContext(),
                        "antivirus.db",
                        getApplicationContext().getFilesDir().getAbsolutePath(),
                        "antivirus.db");
            }
        }.start();
    }

    /**
     * 扫描病毒
     */
    private void scanVirus() {
        startScan();
        flag = true;
        isStop = false;
        process = 0;
        mScanAppInfos.clear();
        new Thread() {

            public void run() {
                Message msg = Message.obtain();
                msg.what = SCAN_BENGIN;
                mHandler.sendMessage(msg);
                List<PackageInfo> installedPackages = pm
                        .getInstalledPackages(0);
                total = installedPackages.size();
                for (PackageInfo info : installedPackages) {
                    if (!flag) {
                        isStop = true;
                        return;
                    }
                    String apkpath = info.applicationInfo.sourceDir;
                    // 检查获取这个文件的 特征码
                    String md5info = MD5Util.md5(apkpath);
                    String result = AntiVirusDao.checkVirus(getApplicationContext(), md5info);
                    msg = Message.obtain();
                    msg.what = SCANNING;
                    ScanAppInfo scanInfo = new ScanAppInfo();
                    if (result == null) {
                        scanInfo.setDescription("扫描安全");
                        scanInfo.setVirus(false);
                    } else {
                        mAntRadarView.addPoint();//每扫描到一个病毒就增加一个亮点
                        scanInfo.setDescription(result);
                        scanInfo.setVirus(true);
                    }
                    process++;
                    scanInfo.setPackagename(info.packageName);
                    scanInfo.setAppName(info.applicationInfo.loadLabel(pm)
                            .toString());
                    scanInfo.setAppicon(info.applicationInfo.loadIcon(pm));
                    msg.obj = scanInfo;
                    msg.arg1 = process;
                    mHandler.sendMessage(msg);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                msg = Message.obtain();
                msg.what = SCAN_FINISH;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    //返回按钮
    public void btnBack(View v) {
        finish();
    }

    //旋转动画
    private void startRotate() {
        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotating_forever);
        rotateAnim.setDuration(3000);
        LinearInterpolator lir = new LinearInterpolator();
        rotateAnim.setInterpolator(lir);
    }

    //开始扫描
    private void startScan() {
        //更换图片
        mAntScanImg.setImageResource(R.mipmap.radar_scan_img);
        mAntScanImg.setPadding(0, 0, 0, 0);
        //开始动画
        mAntScanImg.setAnimation(rotateAnim);
        mProcessTv.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ant_start_btn:
                if (process == total & process > 0) { // 扫描已完成
                    scanVirus();
                } else if (process > 0 & process < total & !isStop) {
                    // 取消扫描
                    mAntScanImg.clearAnimation();//清除动画
                    mAntScanImg.setImageResource(R.mipmap.ant_nofinish);
                    mAntStartBtn.setText(R.string.activity_ant_start);
                    mAntScannerTv.setText(R.string.activity_ant_scan_nofinish);
                    mProcessTv.setVisibility(View.INVISIBLE);
                    int paddingDP = DensityUtil.dip2px(getApplicationContext(), 30);
                    mAntScanImg.setPadding(paddingDP, paddingDP, paddingDP, paddingDP);
                    flag = false;
                } else if (isStop) {
                    // 重新扫描
                    scanVirus();
                } else {
                    //开始扫描
                    scanVirus();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        flag = false;
        super.onDestroy();
    }
}
