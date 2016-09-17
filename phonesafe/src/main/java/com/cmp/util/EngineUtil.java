package com.cmp.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.cmp.data.AppInfo;
import com.stericson.RootTools.RootTools;

/**
 * 作者：cmp on 2016/9/17 13:18
 */
public class EngineUtil {
    /**
     * 分享程序
     */
    private void shareActivity(Context context, AppInfo appInfo) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "推荐你使用一款软件名字叫" + appInfo.getName());
        context.startActivity(intent);
    }

    /**
     * 开启一个程序
     */
    private void startActivity(Context context, AppInfo appInfo) {
        PackageManager pm = context.getPackageManager();
        String packageName = appInfo.getPackageName();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "对不起不能启动该应用", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 卸载一个程序
     */
    private void unInstallActivity(Context context, AppInfo appInfo) {
        if (appInfo.isUserApp()) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setAction("android.intent.action.DELETE");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + appInfo.getPackageName()));
            context.startActivity(intent);
        } else {
            if (!RootTools.isRootAvailable()) {
                Toast.makeText(context, "卸载系统应用需要Root权限", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                if (!RootTools.isAccessGiven()) {
                    Toast.makeText(context, "请授权C盘卫士Root权限", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开启设置界面
     */
    public static void SettingAppDetail(Context context, AppInfo appInfo) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.CATEGORY_DEFAULT");
        intent.setData(Uri.parse("package:" + appInfo.getPackageName()));
        context.startActivity(intent);
    }
}
