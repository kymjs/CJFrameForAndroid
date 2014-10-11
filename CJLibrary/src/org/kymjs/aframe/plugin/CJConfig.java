package org.kymjs.aframe.plugin;

/**
 * CJFrame中的常量声明配置器
 * 
 * @author kymjs(kymjs123@gmail.com)
 * 
 */
public class CJConfig {
    public static final String FROM = "extra.from";
    public static final int FROM_INTERNAL = 0;
    public static final int FROM_EXTERNAL = 1;

    public static final String EXTRA_DEX_PATH = "extra.dex.path";
    public static final String EXTRA_CLASS = "extra.class";

    public static enum ActivityType {
        UNKNOWN, NORMAL, FRAGMENT, ACTIONBAR
    }

    public static final String PROXY_ACTIVITY = "org.kymjs.cjframe.activity";
    public static final String PROXY_FRAGMENT = "org.kymjs.cjframe.fragment";
}
