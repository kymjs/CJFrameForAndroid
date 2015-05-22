package com.lody.plugin.bean;

import android.app.Service;

import com.lody.plugin.manager.LApkManager;

public class LServicePlugin {
    /**
     * 插件所属apk
     */
    LAPK from;
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

    public LServicePlugin(Service proxyParent, String apkPath) {
        this.proxyParent = proxyParent;
        from = LApkManager.get(apkPath);
        from.bindDexLoader(proxyParent);
        // NOTE:此时from这个对象可能没有初始化，也可能已经初始化了，为了保证效率，需要判断。
        // 可以通过from.canUse()来判断。
    }

    public LAPK from() {
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
