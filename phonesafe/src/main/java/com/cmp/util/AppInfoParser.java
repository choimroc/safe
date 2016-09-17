package com.cmp.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.cmp.data.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：cmp on 2016/9/17 01:40
 */
public class AppInfoParser {
    /**
     * 获取手机里面的所有的应用程序
     * @param context 上下文
     * @return
     */
    public static List<AppInfo> getappInfos(Context context){
        //得到一个java保证的 包管理器。
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packInfos = pm.getInstalledPackages(0);
        List<AppInfo> appInfos = new ArrayList<>();
        for(PackageInfo packInfo:packInfos){
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
            long appSize = file.length();
            appInfo.setAppSize(appSize);
            //应用程序安装的位置。
            int flags = packInfo.applicationInfo.flags; //二进制映射  大bit-map
            if((ApplicationInfo.FLAG_EXTERNAL_STORAGE&flags)!=0){
                //外部存储
                appInfo.setInRom(false);
            }else{
                //手机内存
                appInfo.setInRom(true);
            }
            if((ApplicationInfo.FLAG_SYSTEM&flags)!=0){
                //系统应用
                appInfo.setUserApp(false);
            }else{
                //用户应用
                appInfo.setUserApp(true);
            }
            appInfos.add(appInfo);
            appInfo = null;
        }
        return appInfos;
    }
}
