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
package org.kymjs.cjframe.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kymjs.cjframe.api.LPluginBugListener;
import org.kymjs.cjframe.api.PluginException;


/**
 * Created by lody on 2015/4/3.
 */
public class LPluginBugManager {
    private static final List<LPluginBugListener> errorCollection = new ArrayList<LPluginBugListener>();

    /**
     * 加入一个插件异常监听器
     * 
     * @param lPluginBugListener
     */
    public static void addBugListener(LPluginBugListener lPluginBugListener) {
        errorCollection.add(lPluginBugListener);
    }

    /**
     * 移除一个插件异常监听器
     * 
     * @param lPluginBugListener
     */
    public static void removeBugListener(LPluginBugListener lPluginBugListener) {
        errorCollection.remove(lPluginBugListener);
    }

    public static void callAllBugListener(final PluginException error) {
        if (errorCollection.size() == 0)
            return;
        Iterator<LPluginBugListener> iterator = errorCollection.iterator();
        while (iterator.hasNext()) {
            iterator.next().OnError(error);
        }
    }

}
