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
package org.kymjs.cjframe.control;

import org.kymjs.cjframe.CJActivityUtils;
import org.kymjs.cjframe.CJConfig;
import org.kymjs.cjframe.manager.CJClassLoader;
import org.kymjs.cjframe.reflect.Reflect;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by lody on 2015/3/27.
 * 
 * @author Lody
 * 
 *         负责转移插件的跳转目标<br>
 * @see android.app.Activity#startActivity(android.content.Intent)
 */
public class PluginInstrument extends Instrumentation {

    private static final String TAG = PluginInstrument.class.getSimpleName();
    Instrumentation pluginIn;
    Reflect instrumentRef;

    public PluginInstrument(Instrumentation pluginIn) {
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
        intent.setClass(who, CJActivityUtils.class);

        Log.i(TAG, "Jump to " + className + "[" + CJClassLoader.finalApkPath
                + "]");

        intent.putExtra(CJConfig.KEY_PLUGIN_DEX_PATH,
                CJClassLoader.finalApkPath);
        intent.putExtra(CJConfig.KEY_PLUGIN_ACT_NAME, className);

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
        intent.setClass(who, CJActivityUtils.class);

        Log.i(TAG, "Jump to " + className + "[" + CJClassLoader.finalApkPath
                + "]");

        intent.putExtra(CJConfig.KEY_PLUGIN_DEX_PATH,
                CJClassLoader.finalApkPath);
        intent.putExtra(CJConfig.KEY_PLUGIN_ACT_NAME, className);

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
