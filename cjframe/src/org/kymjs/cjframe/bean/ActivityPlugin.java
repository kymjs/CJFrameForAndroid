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
package org.kymjs.cjframe.bean;

import org.kymjs.cjframe.control.PluginActivityControl;
import org.kymjs.cjframe.manager.ApkManager;

import android.app.Activity;
import android.content.res.Resources;


/**
 * Created by lody on 2015/3/27.
 */
public class ActivityPlugin {
    /**
     * 插件所属apk
     */
    AndroidPackage from;
    /**
     * 插件主题
     */
    private Resources.Theme currentPluginTheme;
    /**
     * 插件代理器
     */
    private Activity proxyParent;
    /**
     * 插件实体Activity
     */
    private Activity CurrentPluginActivity;
    /**
     * 插件的第一个Activity
     */
    private String topActivityName = null;
    /**
     * 插件控制器
     */
    private PluginActivityControl control;

    /**
     * 得到处于顶部的插件Activity名
     * 
     * @return 处于顶部的插件Activity名
     */
    public String getTopActivityName() {
        return topActivityName;
    }

    /**
     * 设置处于顶部的插件Activity名
     * 
     * @param topActivityName
     *            处于顶部的插件Activity名
     */
    public void setTopActivityName(String topActivityName) {
        this.topActivityName = topActivityName;
    }

    /**
     * 
     * @return 插件控制器
     */
    public PluginActivityControl getControl() {
        return control;
    }

    /**
     * 设置插件控制器
     * 
     * @param control
     */
    public void setControl(PluginActivityControl control) {
        this.control = control;
    }

    /**
     * 得到代理的实体
     * 
     * @return
     */
    public Activity getProxyParent() {
        return proxyParent;
    }

    /**
     * 设置代理的实体
     * 
     * @param proxyParent
     */
    public void setProxyParent(Activity proxyParent) {
        this.proxyParent = proxyParent;
    }

    /**
     * 
     * @return 插件所在路径
     */
    public String getPluginPath() {
        return from.pluginPath;
    }

    /**
     * 得到当前主题
     * 
     * @return 当前主题
     */
    public Resources.Theme getTheme() {
        return currentPluginTheme;
    }

    /**
     * 设置当前主题
     * 
     * @param currentPluginTheme
     *            当前主题
     */
    public void setTheme(Resources.Theme currentPluginTheme) {
        this.currentPluginTheme = currentPluginTheme;
    }

    /**
     * @return 当前的插件Activity
     */
    public Activity getCurrentPluginActivity() {
        return CurrentPluginActivity;
    }

    public void setCurrentPluginActivity(Activity currentPluginActivity) {
        CurrentPluginActivity = currentPluginActivity;
    }

    /**
     * 插件是否已经可以正常使用？
     * 
     * @return
     */
    public boolean canUse() {
        return proxyParent != null && getCurrentPluginActivity() != null;
    }

    public AndroidPackage from() {
        return from;
    }

    public ActivityPlugin(Activity proxyParent, String apkPath) {
        this.proxyParent = proxyParent;
        from = ApkManager.get(apkPath);
        from.bindDexLoader(proxyParent);
        // NOTE:此时from这个对象可能没有初始化，也可能已经初始化了，为了保证效率，需要判断。
        // 可以通过from.canUse()来判断。
    }
}
