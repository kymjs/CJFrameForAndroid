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
package org.kymjs.cjframe.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kymjs.cjframe.CJTool;
import org.kymjs.cjframe.bean.PluginInfo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;


/**
 * Created by lody on 2015/4/4.
 */
public class LPluginSearcher {

    public static List<PluginInfo> listAllPluginFromDirectory(
            final Context context, String directory) {
        File dir = new File(directory);
        if (!dir.isDirectory() || !dir.exists()) {
            return null;
        }

        File[] files = dir.listFiles();
        if (files.length == 0) {
            return null;
        }
        final List<File> apks = filterApk(files);
        if (apks.size() == 0) {
            return null;
        }
        final List<PluginInfo> infos = new ArrayList<PluginInfo>();

        Iterator<File> iterator = apks.iterator();
        while (iterator.hasNext()) {
            onNewElement(context, iterator.next(), infos);
        }

        return infos;

    }

    public static void onNewElement(Context context, File element,
            List<PluginInfo> infos) {
        String filePath = element.getAbsolutePath();
        Drawable drawable = null;
        try {
            drawable = CJTool.getAppIcon(context, filePath);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        PluginInfo info = new PluginInfo();
        info.setApkIcon(drawable);
        info.setApkPath(filePath);
        infos.add(info);

    }

    public static List<File> filterApk(File[] files) {
        final List<File> apks = new ArrayList<File>();
        for (File file : files) {
            // 根据后缀判断是否是一个apk
            if (!file.getName().toLowerCase().endsWith(".apk")) {
                continue;
            }
            apks.add(file);
        }
        return apks;
    }
}
