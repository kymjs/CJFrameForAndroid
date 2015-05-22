package com.lody.plugin.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;

import com.lody.plugin.NativeLibUnpacker;
import com.lody.plugin.reflect.Reflect;

import dalvik.system.DexClassLoader;

/**
 * Created by lody on 2015/3/24. 插件的核心加载器<br>
 * 已支持Native
 */
public class LPluginDexManager extends DexClassLoader {

    private static final Map<String, LPluginDexManager> pluginLoader = new ConcurrentHashMap<String, LPluginDexManager>();
    public static String finalApkPath;

    protected LPluginDexManager(String dexPath, String optimizedDirectory,
            String libraryPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, libraryPath, parent);
        finalApkPath = dexPath;
        NativeLibUnpacker.unPackSOFromApk(dexPath, libraryPath);
    }

    /**
     * 返回apk对应的加载器，不会重复加载同样的资源
     */
    public static LPluginDexManager getClassLoader(String dexPath, Context cxt,
            ClassLoader parent) {
        LPluginDexManager pluginDexLoader = pluginLoader.get(dexPath);
        if (pluginDexLoader == null) {
            // 获取到app的启动路径
            final String dexOutputPath = cxt.getDir("plugin",
                    Context.MODE_PRIVATE).getAbsolutePath();
            final String libOutputPath = cxt.getDir("plugin_lib",
                    Context.MODE_PRIVATE).getAbsolutePath();

            pluginDexLoader = new LPluginDexManager(dexPath, dexOutputPath,
                    libOutputPath, parent);
            pluginLoader.put(dexPath, pluginDexLoader);
        }
        return pluginDexLoader;
    }

    public static ClassLoader getSystemLoader() {
        Context context = Reflect.on("android.app.ActivityThread")
                .call("currentActivityThread").call("getSystemContext").get();
        return context == null ? null : context.getClassLoader();
    }
}
