package com.lody.plugin.manager;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;

import com.lody.plugin.EasyFor;
import com.lody.plugin.control.PluginActivityCallback;

/**
 * Created by lody on 2015/3/31.
 */
public class LCallbackManager {
    private static final ArrayList<PluginActivityCallback> pluginsMapForPath = new ArrayList<PluginActivityCallback>();

    public static void addActivityCallback(PluginActivityCallback callback) {

        pluginsMapForPath.add(callback);

    }

    public static void removeActivityCallback(PluginActivityCallback callback) {

        pluginsMapForPath.remove(callback);
    }

    public static void callAllOnCreate(final Bundle args) {

        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnCreate(args);
            }
        };
    }

    public static void callAllOnStart() {

        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnStart();
            }
        };
    }

    public static void callAllOnStop() {

        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnStop();
            }
        };
    }

    public static void callAllOnResume() {

        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnResume();
            }
        };
    }

    public static void callAllOnRestart() {

        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnRestart();

            }
        };
    }

    public static void callAllOnBackPressed() {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnBackPressed();

            }
        };
    }

    public static void callAllOnDestroy() {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnDestroy();

            }
        };
    }

    public static void callAllOnSaveInstanceState(final Bundle out) {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnSaveInstanceState(out);

            }
        };

    }

    public static void callAllOnRestoreInstanceState(
            final Bundle savedInstanceState) {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnRestoreInstanceState(savedInstanceState);

            }
        };

    }

    public static void callAllOnKeyDown(final int keyCode, final KeyEvent event) {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnKeyDown(keyCode, event);

            }
        };
    }

    public static void callAllOnPause() {
        new EasyFor<PluginActivityCallback>(pluginsMapForPath) {
            @Override
            public void onNewElement(PluginActivityCallback element) {
                element.callOnPause();
            }
        };
    }
}