package com.lody.plugin.manager;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.lody.plugin.LPluginTool;
import com.lody.plugin.bean.LAPK;
import com.lody.plugin.exception.NotFoundPluginException;
import com.lody.plugin.exception.PluginCreateFailedException;
import com.lody.plugin.exception.PluginNotExistException;
import com.lody.plugin.reflect.Reflect;

/**
 * Created by lody on 2015/4/6.
 */
public final class LApkManager {

    private static final Map<String, LAPK> apks = new ConcurrentHashMap<String, LAPK>();
    private static final String TAG = LApkManager.class.getSimpleName();

    public static LAPK get(String apkPath) {
        LAPK apk;
        apk = apks.get(apkPath);
        if (apk == null) {
            apk = new LAPK();
            apk.attach(apkPath);
            apks.put(apkPath, apk);
        }
        return apk;
    }

    public static void initApk(LAPK apk, Context ctx) {
        String apkPath = apk.pluginPath;
        File file = new File(apkPath);
        if (!file.exists())
            throw new NotFoundPluginException(apkPath);
        Log.i(TAG, "Init a plugin on" + apkPath);
        if (!apk.canUse()) {
            Log.i(TAG, "Plugin is not been init,init it now！");
            fillPluginInfo(apk, ctx);
            fillPluginRes(apk, ctx);
            fillPluginApplication(apk, ctx);
        } else {
            Log.i(TAG, "Plugin have been init.");
        }

    }

    private static void fillPluginInfo(LAPK apk, Context ctx) {
        PackageInfo info = null;
        try {
            info = LPluginTool.getAppInfo(ctx, apk.pluginPath);
        } catch (PackageManager.NameNotFoundException e) {
            throw new PluginNotExistException(apk.pluginPath);
        }
        if (info == null) {
            throw new PluginCreateFailedException("Can't create Plugin from :"
                    + apk.pluginPath);
        }
        apk.setPluginPkgInfo(info);
        apk.setApplicationName(info.applicationInfo.className);
    }

    private static void fillPluginRes(LAPK apk, Context ctx) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Reflect assetRef = Reflect.on(assetManager);
            assetRef.call("addAssetPath", apk.pluginPath);
            Log.i(TAG, "Assets = " + assetManager);
            apk.setPluginAssets(assetManager);

            Resources pluginRes = new Resources(assetManager, ctx
                    .getResources().getDisplayMetrics(), ctx.getResources()
                    .getConfiguration());
            Log.i(TAG, "Res = " + pluginRes);
            apk.setPluginRes(pluginRes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillPluginApplication(LAPK apk, Context ctx) {
        String applicationName = apk.applicationName;
        if (applicationName == null)
            return;
        if (applicationName.isEmpty())
            return;

        ClassLoader loader = apk.pluginLoader;
        if (loader == null)
            throw new PluginCreateFailedException(
                    "Not found ClassLoader in plugin!");
        try {
            Application pluginApp = (Application) loader.loadClass(
                    applicationName).newInstance();
            Reflect.on(pluginApp).call("attachBaseContext",
                    ctx.getApplicationContext());
            apk.pluginApplication = pluginApp;
            pluginApp.onCreate();

        } catch (InstantiationException e) {
            // throw new PluginCreateFailedException(e.getMessage());
        } catch (IllegalAccessException e) {
            // throw new PluginCreateFailedException(e.getMessage());
        } catch (ClassNotFoundException e) {
            // throw new PluginCreateFailedException(e.getMessage());
        }
    }

}
