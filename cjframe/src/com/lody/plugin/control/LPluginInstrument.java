package com.lody.plugin.control;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.lody.plugin.LActivityProxy;
import com.lody.plugin.LPluginConfig;
import com.lody.plugin.manager.LPluginDexManager;
import com.lody.plugin.reflect.Reflect;

/**
 * Created by lody on 2015/3/27.
 * 
 * @author Lody
 * 
 *         负责转移插件的跳转目标<br>
 * @see android.app.Activity#startActivity(android.content.Intent)
 */
public class LPluginInstrument extends Instrumentation {

    private static final String TAG = LPluginInstrument.class.getSimpleName();
    Instrumentation pluginIn;
    Reflect instrumentRef;

    public LPluginInstrument(Instrumentation pluginIn) {
        this.pluginIn = pluginIn;
        instrumentRef = Reflect.on(pluginIn);
    }

    /** @Override */
    public ActivityResult execStartActivity(Context who, IBinder contextThread,
            IBinder token, Activity target, Intent intent, int requestCode,
            Bundle options) {

        ComponentName componentName = intent.getComponent();
        if (componentName == null) {
            return instrumentRef.call("execStartActivity", who, contextThread,
                    token, target, intent, requestCode, options).get();
        }
        String className = componentName.getClassName();
        intent.setClass(who, LActivityProxy.class);

        Log.i(TAG, "Jump to " + className + "["
                + LPluginDexManager.finalApkPath + "]");

        intent.putExtra(LPluginConfig.KEY_PLUGIN_DEX_PATH,
                LPluginDexManager.finalApkPath);
        intent.putExtra(LPluginConfig.KEY_PLUGIN_ACT_NAME, className);

        return instrumentRef.call("execStartActivity", who, contextThread,
                token, target, intent, requestCode, options).get();

    }

    /** @Override */
    public ActivityResult execStartActivity(Context who, IBinder contextThread,
            IBinder token, Activity target, Intent intent, int requestCode) {

        ComponentName componentName = intent.getComponent();
        if (componentName == null) {
            return instrumentRef.call("execStartActivity", who, contextThread,
                    token, target, intent, requestCode).get();
        }
        String className = componentName.getClassName();
        intent.setClass(who, LActivityProxy.class);

        Log.i(TAG, "Jump to " + className + "["
                + LPluginDexManager.finalApkPath + "]");

        intent.putExtra(LPluginConfig.KEY_PLUGIN_DEX_PATH,
                LPluginDexManager.finalApkPath);
        intent.putExtra(LPluginConfig.KEY_PLUGIN_ACT_NAME, className);

        return instrumentRef.call("execStartActivity", who, contextThread,
                token, target, intent, requestCode).get();

    }

    @Override
    public void onStart() {
        pluginIn.onStart();
    }

    @Override
    public void onCreate(Bundle arguments) {
        pluginIn.onCreate(arguments);
    }

    @Override
    public void onDestroy() {
        pluginIn.onDestroy();
    }

    @Override
    public boolean onException(Object obj, Throwable e) {
        return pluginIn.onException(obj, e);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        pluginIn.callActivityOnCreate(activity, icicle);
    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        pluginIn.callActivityOnNewIntent(activity, intent);
    }

    @Override
    public void callApplicationOnCreate(Application app) {
        pluginIn.callApplicationOnCreate(app);
    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        pluginIn.callActivityOnDestroy(activity);
    }

    @Override
    public void callActivityOnPause(Activity activity) {
        pluginIn.callActivityOnDestroy(activity);
    }

}
