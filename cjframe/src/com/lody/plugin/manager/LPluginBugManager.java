package com.lody.plugin.manager;

import java.util.ArrayList;
import java.util.List;

import com.lody.plugin.EasyFor;
import com.lody.plugin.api.LPluginBug;
import com.lody.plugin.api.LPluginBugListener;

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

    public static void callAllBugListener(final LPluginBug error) {
        if (errorCollection.size() == 0)
            return;
        new EasyFor<LPluginBugListener>(errorCollection) {
            @Override
            public void onNewElement(LPluginBugListener element) {
                element.OnError(error);
            }
        };
    }

}
