package com.lody.plugin.api;

/**
 * Created by lody on 2015/4/3.
 */
public interface LPluginLoadListener {
    void onLoadStart();

    void onLoading();

    void onLoadFinish();
}
