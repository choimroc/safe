package com.cmp.data;

import android.graphics.drawable.Drawable;

/**
 * 作者：cmp on 2016/9/16 17:30
 */
public class AppInfo {
    private String apkPath;

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    /**
     * 应用程序的图标
     */
    private Drawable icon;

    /**
     * 应用程序名称
     */
    private String name;

    /**
     * 应用程序安装的位置，true手机内存 ，false外部存储卡
     */
    private boolean inRom;

    /**
     * 应用程序的大小
     */
    private long appSize;

    /**
     * 是否是用户程序  true 用户程序 false 系统程序
     */
    private boolean userApp;

    /**
     * 应用程序的包名
     */
    private String packageName;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInRom() {
        return inRom;
    }

    public void setInRom(boolean inRom) {
        this.inRom = inRom;
    }

    public long getAppSize() {
        return appSize;
    }

    public void setAppSize(long appSize) {
        this.appSize = appSize;
    }

    public boolean isUserApp() {
        return userApp;
    }

    public void setUserApp(boolean userApp) {
        this.userApp = userApp;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "AppInfo [name=" + name + ", inRom=" + inRom + ", appSize="
                + appSize + ", userApp=" + userApp + ", packageName=" + packageName
                + "]";
    }

}

