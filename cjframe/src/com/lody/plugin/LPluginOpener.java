package com.lody.plugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lody.plugin.service.LProxyService;

/**
 * Created by lody on 2015/3/24.
 */
public class LPluginOpener {

    /**
     * 直接启动一个apk
     * 
     * @param context
     *            当前上下文
     * @param pluginPath
     *            插件路径
     */
    public static void startPlugin(Context context, String pluginPath) {
        Intent i = new Intent(context, LActivityProxy.class);
        Bundle bundle = new Bundle();
        bundle.putString(LPluginConfig.KEY_PLUGIN_DEX_PATH, pluginPath);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    /**
     * 直接启动一个apk
     * 
     * @param context
     *            当前上下文
     * @param pluginPath
     *            插件路径
     * @param args
     *            携带数据
     */
    public static void startPlugin(Context context, String pluginPath,
            Bundle args) {
        Intent i = new Intent(context, LActivityProxy.class);
        args.putString(LPluginConfig.KEY_PLUGIN_DEX_PATH, pluginPath);
        i.putExtras(args);
        context.startActivity(i);
    }

    /**
     * 启动插件中的指定activity
     * 
     * @param context
     * @param pluginPath
     * @param activityName
     *            要启动的插件的activity名
     */
    public static void startActivity(Context context, String pluginPath,
            String activityName) {
        Intent i = new Intent(context, LActivityProxy.class);
        Bundle bundle = new Bundle();
        bundle.putString(LPluginConfig.KEY_PLUGIN_DEX_PATH, pluginPath);
        bundle.putString(LPluginConfig.DEF_PLUGIN_CLASS_NAME, activityName);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    /**
     * 启动插件中的指定activity
     * 
     * @param context
     * @param pluginPath
     * @param activityName
     *            要启动的插件的activity名
     * @param args
     *            携带数据
     */
    public static void startActivity(Context context, String pluginPath,
            String activityName, Bundle args) {
        Intent i = new Intent(context, LActivityProxy.class);
        args.putString(LPluginConfig.KEY_PLUGIN_DEX_PATH, pluginPath);
        args.putString(LPluginConfig.DEF_PLUGIN_CLASS_NAME, activityName);
        i.putExtras(args);
        context.startActivity(i);
    }

    /**
     * 启动插件中的指定service
     * 
     * @param context
     * @param pluginPath
     * @param serviceName
     *            要启动的插件的activity名
     */
    public static void startService(Context context, String pluginPath,
            String serviceName) {
        Intent i = new Intent(context, LProxyService.class);
        LProxyService.SERVICE_APK_PATH = pluginPath;
        LProxyService.SERVICE_CLASS_NAME = serviceName;
        context.startService(i);
    }

    /**
     * 启动插件中的指定service
     * 
     * @param context
     * @param pluginPath
     * @param serviceName
     *            要启动的插件的activity名
     * @param args
     *            携带数据
     */
    public static void startService(Context context, String pluginPath,
            String serviceName, Bundle args) {
        Intent i = new Intent(context, LProxyService.class);
        i.putExtras(args);
        LProxyService.SERVICE_APK_PATH = pluginPath;
        LProxyService.SERVICE_CLASS_NAME = serviceName;
        context.startService(i);
    }
}
