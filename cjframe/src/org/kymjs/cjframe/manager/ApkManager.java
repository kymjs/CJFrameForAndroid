/*
 * Copyright (c) 2014, 张涛, lody.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.cjframe.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.kymjs.cjframe.CJTool;
import org.kymjs.cjframe.bean.AndroidPackage;
import org.kymjs.cjframe.reflect.Reflect;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;


/**
 * Created by lody on 2015/4/6.
 */
public final class ApkManager {

    private static final Map<String, AndroidPackage> apks = new ConcurrentHashMap<String, AndroidPackage>();
    private static final String TAG = ApkManager.class.getSimpleName();

    public static AndroidPackage get(String apkPath) {
        AndroidPackage apk;
        apk = apks.get(apkPath);
        if (apk == null) {
            apk = new AndroidPackage();
            apk.attach(apkPath);
            apks.put(apkPath, apk);
        }
        return apk;
    }

    public static void initApk(AndroidPackage apk, Context ctx) {
        String apkPath = apk.pluginPath;
        File file = new File(apkPath);
        if (!file.exists())
            try {
                throw new FileNotFoundException(apkPath);
            } catch (FileNotFoundException e) {
            }
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

    private static void fillPluginInfo(AndroidPackage apk, Context ctx) {
        PackageInfo info = null;
        try {
            info = CJTool.getAppInfo(ctx, apk.pluginPath);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("file not found" + apk.pluginPath);
        }
        if (info == null) {
            throw new RuntimeException("Can't create Plugin from :"
                    + apk.pluginPath);
        }
        apk.setPluginPkgInfo(info);
        apk.setApplicationName(info.applicationInfo.className);
    }

    private static void fillPluginRes(AndroidPackage apk, Context ctx) {
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

    private static void fillPluginApplication(AndroidPackage apk, Context ctx) {
        String applicationName = apk.applicationName;
        if (applicationName == null)
            return;
        if (applicationName.isEmpty())
            return;

        ClassLoader loader = apk.pluginLoader;
        if (loader == null)
            throw new RuntimeException("Not found ClassLoader in plugin!");
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
