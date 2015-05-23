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
package org.kymjs.cjframe;

/**
 * 保存插件的配置信息
 */
public class CJConfig {
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
