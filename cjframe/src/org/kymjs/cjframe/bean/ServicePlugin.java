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

import org.kymjs.cjframe.manager.ApkManager;

import android.app.Service;


public class ServicePlugin {
    /**
     * 插件所属apk
     */
    AndroidPackage from;
    /**
     * 插件代理器
     */
    private Service proxyParent;
    /**
     * 插件实体Service
     */
    private Service CurrentPluginService;
    /**
     * 插件的第一个Service
     */
    private String topServiceName = null;

    public ServicePlugin(Service proxyParent, String apkPath) {
        this.proxyParent = proxyParent;
        from = ApkManager.get(apkPath);
        from.bindDexLoader(proxyParent);
        // NOTE:此时from这个对象可能没有初始化，也可能已经初始化了，为了保证效率，需要判断。
        // 可以通过from.canUse()来判断。
    }

    public AndroidPackage from() {
        return from;
    }

    /**
     * 设置代理的实体
     * 
     * @param proxyParent
     */
    public void setProxyParent(Service proxyParent) {
        this.proxyParent = proxyParent;
    }

    /**
     * 得到代理的实体
     * 
     * @return
     */
    public Service getProxyParent() {
        return proxyParent;
    }

    public void setCurrentPluginService(Service currentPluginService) {
        CurrentPluginService = currentPluginService;
    }

    /**
     * @return 当前的插件Service
     */
    public Service getCurrentPluginService() {
        return CurrentPluginService;
    }

    /**
     * 设置当前的插件Service
     * 
     */
    public void setTopServiceName(String topServiceName) {
        this.topServiceName = topServiceName;
    }

    public String getTopServiceName() {
        return topServiceName;
    }

    /**
     * 插件是否已经可以正常使用？
     * 
     * @return
     */
    public boolean canUse() {
        return proxyParent != null && getCurrentPluginService() != null;
    }
}
