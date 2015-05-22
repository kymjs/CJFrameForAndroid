package com.lody.plugin.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;

import com.lody.plugin.bean.LServicePlugin;
import com.lody.plugin.manager.LApkManager;
import com.lody.plugin.manager.LPluginDexManager;
import com.lody.plugin.reflect.Reflect;

public class LProxyService extends Service {

    LServicePlugin remote;
    public static String SERVICE_CLASS_NAME = Service.class.getName();
    public static String SERVICE_APK_PATH = LPluginDexManager.finalApkPath;

    @Override
    public IBinder onBind(Intent i) {
        return remote.getCurrentPluginService().onBind(i);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!remote.getCurrentPluginService().getClass().getName()
                .equals(SERVICE_CLASS_NAME)) {
            fillService();
            remote.getCurrentPluginService().onCreate();
        }
        return remote.getCurrentPluginService().onStartCommand(intent, flags,
                startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fillService();
        remote.getCurrentPluginService().onCreate();
    }

    private void fillService() {
        remote = new LServicePlugin(this, SERVICE_APK_PATH);
        remote.setTopServiceName(SERVICE_CLASS_NAME);
        remote.from().debug();
        if (!remote.from().canUse()) {
            LApkManager.initApk(remote.from(), this);
        }

        try {
            Service plugin = (Service) remote.from().pluginLoader.loadClass(
                    remote.getTopServiceName()).newInstance();
            remote.setCurrentPluginService(plugin);
            Reflect thiz = Reflect.on(this);
            Reflect.on(plugin).call("attach", this, thiz.get("mThread"),
                    getClass().getName(), thiz.get("mToken"), getApplication(),
                    thiz.get("mActivityManager"));

        } catch (IllegalAccessException e) {
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        remote.getCurrentPluginService().onStart(intent, startId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        remote.getCurrentPluginService().onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return remote.getCurrentPluginService().onUnbind(intent);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        remote.getCurrentPluginService().onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {

        super.onRebind(intent);
        remote.getCurrentPluginService().onRebind(intent);
    }

    @Override
    public void onTrimMemory(int level) {

        super.onTrimMemory(level);
        remote.getCurrentPluginService().onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
        remote.getCurrentPluginService().onLowMemory();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {

        super.onTaskRemoved(rootIntent);
        remote.getCurrentPluginService().onTaskRemoved(rootIntent);
    }

    @Override
    public Resources getResources() {
        if (remote == null)
            return super.getResources();
        return remote.from().pluginRes == null ? super.getResources() : remote
                .from().pluginRes;
    }

    @Override
    public AssetManager getAssets() {
        if (remote == null)
            return super.getAssets();
        return remote.from().pluginAssets == null ? super.getAssets() : remote
                .from().pluginAssets;
    }

    @Override
    public ClassLoader getClassLoader() {
        if (remote == null) {
            return super.getClassLoader();
        }
        if (remote.from().canUse()) {
            return remote.from().pluginLoader;
        }
        return super.getClassLoader();
    }

    @Override
    public ComponentName startService(Intent service) {
        LProxyService.SERVICE_CLASS_NAME = service.getComponent()
                .getClassName();
        service.setClass(this, LProxyService.class);
        return super.startService(service);
    }

}
