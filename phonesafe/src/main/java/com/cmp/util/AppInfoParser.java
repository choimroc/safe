package com.cmp.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;

import com.cmp.data.AppInfo;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：cmp on 2016/9/17 01:40
 */
public class AppInfoParser {
    private static List<AppInfo> appInfos = new ArrayList<>();

    /**
     * 获取手机里面的所有的应用程序
     *
     * @param context 上下文
     * @return
     */
    public static List<AppInfo> getappInfos(Context context) {
        //得到一个java保证的包管理器。
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packInfos = pm.getInstalledPackages(0);

        for (PackageInfo packInfo : packInfos) {
            AppInfo appInfo = new AppInfo();
            String packName = packInfo.packageName;
            appInfo.setPackageName(packName);
            Drawable icon = packInfo.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);
            String appName = packInfo.applicationInfo.loadLabel(pm).toString();
            appInfo.setName(appName);
            //应用程序apk包的路径
            String apkPath = packInfo.applicationInfo.sourceDir;
            appInfo.setApkPath(apkPath);
            File file = new File(apkPath);
            //最后一次安装的时间
            Date installDate = new Date(file.lastModified());//时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            appInfo.setAppDate(format.format(installDate));
            //应用大小
            long appSize = file.length();
            appInfo.setAppSize(appSize);
            //应用缓存大小
            getAppSize(packName, pm);
            //应用版本号
            String appVersion = packInfo.versionName;
            appInfo.setAppVersion(appVersion);
            //应用程序安装的位置。
            int flags = packInfo.applicationInfo.flags; //二进制映射  大bit-map
            if ((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags) != 0) {
                //外部存储
                appInfo.setInRom(false);
            } else {
                //手机内存
                appInfo.setInRom(true);
            }
            if ((ApplicationInfo.FLAG_SYSTEM & flags) != 0) {
                //系统应用
                appInfo.setUserApp(false);
            } else {
                //用户应用
                appInfo.setUserApp(true);
            }
            appInfos.add(appInfo);
            appInfo = null;
        }
        return appInfos;
    }

    private static void getAppSize(final String packName, PackageManager pm) {
        try {
            Method method = PackageManager.class.getMethod("getPackageSizeInfo", String.class,
                    IPackageStatsObserver.class);
            method.invoke(pm, packName,
                    new IPackageStatsObserver.Stub() {
                        public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                            // 注意这个操作是一个异步的操作
                            long cachesize = pStats.cacheSize;
                            long codesize = pStats.codeSize;
                            long datasize = pStats.dataSize;
                            AppInfo appInfo = new AppInfo();
                            appInfo.setAppCacheSize(cachesize);
                            appInfos.add(appInfo);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
