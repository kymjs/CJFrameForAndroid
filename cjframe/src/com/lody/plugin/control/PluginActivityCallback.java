package com.lody.plugin.control;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by lody on 2015/3/26.
 */
public interface PluginActivityCallback {

    void callOnCreate(Bundle saveInstance);

    void callOnStart();

    void callOnResume();

    void callOnDestroy();

    void callOnStop();

    void callOnRestart();

    void callOnSaveInstanceState(Bundle outState);

    void callOnRestoreInstanceState(Bundle savedInstanceState);

    void callOnPause();

    void callOnBackPressed();

    boolean callOnKeyDown(int keyCode, KeyEvent event);

    void callDump(String prefix, FileDescriptor fd, PrintWriter writer,
            String[] args);

    void callOnPostResume();

    void callOnDetachedFromWindow();

    View callOnCreateView(String name, Context context, AttributeSet attrs);

    View callOnCreateView(View parent, String name, Context context,
            AttributeSet attrs);

    void callOnNewIntent(Intent intent);

    void callOnConfigurationChanged(Configuration newConfig);
}
