package com.cmp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cmp.myapplication.R;
import com.cmp.view.ImageRadarView;

/**
 * 作者：cmp on 2016/9/9 15:34
 */
public class RadarActivity extends Activity {
    private ImageRadarView radarView;
    private Thread radarSweepThread;
    private boolean startRadar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        radarView = (ImageRadarView) findViewById(R.id.image_radar);
        radarSweepThread = new Thread(new RadarSweep());// 雷达扫描线程
        radarSweepThread.start();
        startRadar = false;
        // radarSweepThread.interrupt();// 停止扫描更新
        // startRadar = true;
    }

    /**
     * @ClassName RadarSweep
     * @Description 雷达扫描动画刷新线程
     */
    private class RadarSweep implements Runnable {
        int i = 1;

        @Override
        public void run() {
            // TODO Auto-generated method stub

            while (!Thread.currentThread().isInterrupted() && i == 1) {
                try {
                    radarView.postInvalidate();// 刷新radarView, 执行onDraw();
                    Thread.sleep(10);// 暂停当前线程，更新UI线程
                } catch (InterruptedException e) {
                    i = 0;// 结束当前扫描线程标志
                    break;
                }
            }
        }

    }

}