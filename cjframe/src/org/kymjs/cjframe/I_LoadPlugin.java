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

import org.kymjs.cjframe.bean.ActivityPlugin;

import android.app.Activity;


public interface I_LoadPlugin {
    /**
     * 根据apk路径加载一个插件
     * 
     * @param proxyParent
     *            代理activity
     * @param apkPath
     *            apk路径
     * @return 插件的bean
     */
    ActivityPlugin loadPlugin(Activity proxyParent, String apkPath);

    /**
     * 根据apk路径加载一个插件
     * 
     * @param proxyParent
     *            代理activity
     * @param apkPath
     *            apk路径
     * @param activityName
     *            启动的activity名
     * @return
     */
    ActivityPlugin loadPlugin(Activity proxyParent, String apkPath,
            String activityName);

    /**
     * 装载一个插件<br>
     * 一个刚刚初始化的插件只有apk路径信息， 本操作能够使其变得完整(装载资源，类加载器等)。
     * 
     * @param plugin
     *            需要装载的插件
     */
    void fillPlugin(ActivityPlugin plugin);

}
