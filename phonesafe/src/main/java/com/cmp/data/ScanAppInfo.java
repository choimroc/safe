package com.cmp.data;

import android.graphics.drawable.Drawable;
/**
 * 作者：cmp on 2016/10/12 11:11
 * <p>
 * 病毒的实体类
 */
public class ScanAppInfo {
	private String appName;//名字
	private boolean isVirus;//是否为病毒
	private String packagename;//包名
	private String description;//描述
	private Drawable appicon;//图标

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public boolean isVirus() {
		return isVirus;
	}

	public void setVirus(boolean virus) {
		isVirus = virus;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Drawable getAppicon() {
		return appicon;
	}

	public void setAppicon(Drawable appicon) {
		this.appicon = appicon;
	}

}
