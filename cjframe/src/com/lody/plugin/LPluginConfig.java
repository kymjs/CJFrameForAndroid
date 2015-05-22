package com.lody.plugin;

/**
 * Created by lody on 2015/3/24.
 * <p/>
 * 保存插件的配置信息
 */
public class LPluginConfig {
    /**
     * 当没有代理Activity时的默认值
     */
    public static final String DEF_PLUGIN_CLASS_NAME = "no_activity_proxy_now";
    /**
     * 当没有插件Dex路径时的默认值
     */
    public static final String DEF_PLUGIN_DEX_PATH = "no_dex_path";

    /**
     * 获取插件activity类名的Key
     */
    public static final String KEY_PLUGIN_ACT_NAME = "plugin_act_name";
    /**
     * 获取插件dex的key
     */
    public static final String KEY_PLUGIN_DEX_PATH = "plugin_dex_path";
    /**
     * 插件Activity的索引值(AndroidManifest.xml里的第几个Activity？)
     */
    public static final String KEY_PLUGIN_INDEX = "activity_index";

    /**
     * 是否使用插件的标题
     */
    public static boolean usePluginTitle = true;
    /**
     * ProxyService需要托管新的Service
     */
    public static boolean FLAG_NEW_SERVICE;

}
