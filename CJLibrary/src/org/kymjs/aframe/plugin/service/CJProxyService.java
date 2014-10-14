package org.kymjs.aframe.plugin.service;

import org.kymjs.aframe.plugin.I_Proxy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CJProxyService extends Service implements I_Proxy {

    private int mAtyIndex; // 插件Activity在插件Manifest.xml中的序列（可选）
    private String mClass; // 插件Activity的完整类名（可选）
    private String mDexPath; // 插件所在绝对路径（必传）

    protected I_CJService mPluginService; // 插件Service对象

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {}
}
