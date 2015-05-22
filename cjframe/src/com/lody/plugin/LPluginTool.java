package com.lody.plugin;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by lody on 2015/3/24.
 */
public class LPluginTool {
    /**
     * 获取一个apk的信息
     * 
     * @param cxt
     *            应用上下文
     * @param apkPath
     *            apk所在绝对路径
     * @return
     */
    public static PackageInfo getAppInfo(Context cxt, String apkPath)
            throws PackageManager.NameNotFoundException {
        PackageManager pm = cxt.getPackageManager();
        PackageInfo pkgInfo = null;
        pkgInfo = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        return pkgInfo;
    }

    /**
     * 获取一个apk中，在Manifest.xml中声明的第一个Activity的信息
     * 
     * @param cxt
     *            应用上下文
     * @param apkPath
     *            apk所在绝对路径
     * @return
     */
    public static ActivityInfo getActivityInfo(Context cxt, String apkPath)
            throws PackageManager.NameNotFoundException {
        return getActivityInfo(getAppInfo(cxt, apkPath), 0);
    }

    /**
     * 获取一个apk中，在Manifest.xml中声明的第index个Activity的信息<br>
     * <b>注意</b>index的大小不正确可能会报ArrayIndexOutOfBoundsException
     * 
     * @param cxt
     *            应用上下文
     * @param apkPath
     *            apk所在绝对路径
     * @param index
     *            要获取的Activity在Manifest.xml中声明的序列（从0开始）
     * @return
     * @throws ArrayIndexOutOfBoundsException
     *             index超出范围会报
     */
    public static ActivityInfo getActivityInfo(Context cxt, String apkPath,
            int index) throws PackageManager.NameNotFoundException {
        return getActivityInfo(getAppInfo(cxt, apkPath), index);
    }

    /**
     * 获取一个apk中，在Manifest.xml中声明的第index个Activity的信息<br>
     * <b>注意</b>index的大小不正确可能会报ArrayIndexOutOfBoundsException
     * 
     * @param pkgInfo
     *            Activity所在应用的PackageInfo
     * @param index
     *            Activity在插件Manifest.xml中的序列（从0开始）
     * @return
     * @throws ArrayIndexOutOfBoundsException
     *             index超出范围会报
     */
    public static ActivityInfo getActivityInfo(PackageInfo pkgInfo, int index) {
        return pkgInfo.activities[index];
    }

    /**
     * 获取应用图标
     * 
     * @param cxt
     *            应用上下文
     * @param apkPath
     *            apk所在绝对路径
     * @return
     */
    public static Drawable getAppIcon(Context cxt, String apkPath)
            throws PackageManager.NameNotFoundException {
        PackageManager pm = cxt.getPackageManager();
        PackageInfo pkgInfo = getAppInfo(cxt, apkPath);
        if (pkgInfo == null) {
            return null;
        } else {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            if (Build.VERSION.SDK_INT >= 8) {
                appInfo.sourceDir = apkPath;
                appInfo.publicSourceDir = apkPath;
            }
            return pm.getApplicationIcon(appInfo);
        }
    }

    /**
     * 获取指定APK应用名
     * 
     * @param cxt
     *            应用上下文
     * @param apkPath
     *            apk所在绝对路径
     * @return
     */
    public static CharSequence getAppName(Context cxt, String apkPath)
            throws PackageManager.NameNotFoundException {
        PackageManager pm = cxt.getPackageManager();
        PackageInfo pkgInfo = getAppInfo(cxt, apkPath);
        if (pkgInfo == null) {
            return null;
        } else {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            if (Build.VERSION.SDK_INT >= 8) {
                appInfo.sourceDir = apkPath;
                appInfo.publicSourceDir = apkPath;
            }
            return pm.getApplicationLabel(appInfo);
        }
    }
}
