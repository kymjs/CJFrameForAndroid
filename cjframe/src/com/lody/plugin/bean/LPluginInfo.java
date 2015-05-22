package com.lody.plugin.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by lody on 2015/4/4.
 */
public class LPluginInfo {

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public Drawable getApkIcon() {
        return apkIcon;
    }

    public void setApkIcon(Drawable apkIcon) {
        this.apkIcon = apkIcon;
    }

    /**
     * apk路径
     */
    String apkPath;
    /**
     * apk图标
     */
    Drawable apkIcon;

}
