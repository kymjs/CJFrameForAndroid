package com.lody.plugin.control;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.lody.plugin.reflect.Reflect;
import com.lody.plugin.reflect.ReflectException;

/**
 * Created by lody on 2015/3/26.
 * <p/>
 * 插件的控制器<br>
 * 派发插件事件和控制插件生命周期
 */
public class PluginActivityControl implements PluginActivityCallback {

    Activity proxy;// 代理Activity
    Activity plugin;// 插件Activity
    Reflect proxyRef;// 指向代理Activity的反射工具类
    Reflect pluginRef;// 指向插件Activity的反射工具类
    Application app;// 分派给插件的Application
    Instrumentation i;

    /**
     * 
     * @param proxy
     *            代理Activity
     * @param plugin
     *            插件Activity
     */
    public PluginActivityControl(Activity proxy, Activity plugin) {
        this(proxy, plugin, null);

    }

    /**
     * 
     * @param proxy
     *            代理Activity
     * @param plugin
     *            插件Activity
     * @param app
     *            分派给插件的Application
     */
    public PluginActivityControl(Activity proxy, Activity plugin,
            Application app) {
        this.proxy = proxy;
        this.plugin = plugin;
        this.app = app;

        i = new Instrumentation();

        // 使反射工具类指向相应的对象
        proxyRef = Reflect.on(proxy);
        pluginRef = Reflect.on(plugin);
    }

    public void dispatchProxyToPlugin() {

        if (plugin.getBaseContext() != null)
            return;
        try {
            // Finals 修改以前的注入方式，采用原生的方式
            Instrumentation instrumentation = proxyRef.get("mInstrumentation");
            pluginRef.call(
            // 方法名
                    "attach",
                    // Context context
                    proxy,
                    // ActivityThread aThread
                    proxyRef.get("mMainThread"),
                    // Instrumentation instr
                    new LPluginInstrument(instrumentation),
                    // IBinder token
                    proxyRef.get("mToken"),
                    // int ident
                    proxyRef.get("mEmbeddedID") == null ? 0 : proxyRef
                            .get("mEmbeddedID"),
                    // Application application
                    app == null ? proxy.getApplication() : app,
                    // Intent intent
                    proxy.getIntent(),
                    // ActivityInfo info
                    proxyRef.get("mActivityInfo"),
                    // CharSequence title
                    proxy.getTitle(),
                    // Activity parent
                    proxy.getParent(),
                    // String id
                    proxyRef.get("mEmbeddedID"),
                    // NonConfigurationInstances lastNonConfigurationInstances
                    proxy.getLastNonConfigurationInstance(),
                    // Configuration config
                    proxyRef.get("mCurrentConfig"));

            pluginRef.set("mWindow", proxy.getWindow());
            plugin.getWindow().setCallback(plugin);
            Reflect.on(proxy.getBaseContext()).call("setOuterContext", plugin);

        } catch (ReflectException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return 插件的Activity
     */
    public Activity getPlugin() {
        return plugin;
    }

    /**
     * 设置插件的Activity
     * 
     * @param plugin
     */
    public void setPlugin(Activity plugin) {
        this.plugin = plugin;
        proxyRef = Reflect.on(plugin);
    }

    /**
     * 得到代理的Activity
     * 
     * @return
     */
    public Activity getProxy() {
        return proxy;
    }

    /**
     * 设置代理的Activity
     * 
     * @param proxy
     */
    public void setProxy(Activity proxy) {
        this.proxy = proxy;
        proxyRef = Reflect.on(proxy);
    }

    /**
     * @return 代理Activity的反射工具类
     */
    public Reflect getProxyRef() {
        return proxyRef;
    }

    /**
     * 
     * @return 插件Activity的反射工具类
     */
    public Reflect getPluginRef() {
        return pluginRef;
    }

    /**
     * 执行插件的onCreate方法
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     * @param saveInstance
     */
    @Override
    public void callOnCreate(Bundle saveInstance) {
        // getPluginRef().call("onCreate", saveInstance);
        i.callActivityOnCreate(plugin, saveInstance);
    }

    /**
     * 执行插件的onStart方法
     * 
     * @see android.app.Activity#onStart()
     */
    @Override
    public void callOnStart() {
        // getPluginRef().call("onStart");
        i.callActivityOnStop(plugin);
    }

    /**
     * 执行插件的onResume方法
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    public void callOnResume() {
        // getPluginRef().call("onResume");
        i.callActivityOnResume(plugin);
    }

    /**
     * 执行插件的onDestroy方法
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    public void callOnDestroy() {
        // getPluginRef().call("onDestroy");
        i.callActivityOnDestroy(plugin);
    }

    /**
     * 执行插件的onStop方法
     * 
     * @see android.app.Activity#onStop()
     */
    @Override
    public void callOnStop() {
        // getPluginRef().call("onStop");
        i.callActivityOnStop(plugin);
    }

    /**
     * 执行插件的onRestart方法
     * 
     * @see android.app.Activity#onRestart()
     */
    @Override
    public void callOnRestart() {
        // getPluginRef().call("onRestart");
        i.callActivityOnRestart(plugin);
    }

    /**
     * 执行插件的onSaveInstanceState方法
     * 
     * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
     * @param outState
     */
    @Override
    public void callOnSaveInstanceState(Bundle outState) {
        getPluginRef().call("onSaveInstanceState", outState);
    }

    /**
     * 执行插件的onRestoreInstanceState方法
     * 
     * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
     * @param savedInstanceState
     */
    @Override
    public void callOnRestoreInstanceState(Bundle savedInstanceState) {
        getPluginRef().call("onRestoreInstanceState", savedInstanceState);
    }

    /**
     * 执行插件的onStop方法
     * 
     * @see android.app.Activity#onStop()
     */
    @Override
    public void callOnPause() {
        // getPluginRef().call("onStop");
        i.callActivityOnPause(plugin);
    }

    /**
     * 执行插件的onBackPressed方法
     * 
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void callOnBackPressed() {
        // getPluginRef().call("onBackPressed");
        plugin.onBackPressed();
    }

    /**
     * 执行插件的onKeyDown方法
     * 
     * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean callOnKeyDown(int keyCode, KeyEvent event) {
        return plugin.onKeyDown(keyCode, event);
        // return (Boolean)getPluginRef().call("onKeyDown", keyCode,
        // event).get();
    }

    // Finals ADD 修复Fragment BUG
    @Override
    public void callDump(String prefix, FileDescriptor fd, PrintWriter writer,
            String[] args) {
        // getPluginRef().call("dump", prefix, fd, writer, args);
        plugin.dump(prefix, fd, writer, args);
    }

    @Override
    public void callOnConfigurationChanged(Configuration newConfig) {
        // getPluginRef().call("onConfigurationChanged", newConfig);
        plugin.onConfigurationChanged(newConfig);
    }

    @Override
    public void callOnPostResume() {
        getPluginRef().call("onPostResume");

    }

    @Override
    public void callOnDetachedFromWindow() {
        // getPluginRef().call("onDetachedFromWindow");
        plugin.onDetachedFromWindow();
    }

    @Override
    public View callOnCreateView(String name, Context context,
            AttributeSet attrs) {
        return plugin.onCreateView(name, context, attrs);
        // return getPluginRef().call("onCreateView", name, context,
        // attrs).get();
    }

    @Override
    public View callOnCreateView(View parent, String name, Context context,
            AttributeSet attrs) {
        return plugin.onCreateView(parent, name, context, attrs);
        // return getPluginRef().call("onCreateView", parent, name, context,
        // attrs).get();
    }

    @Override
    public void callOnNewIntent(Intent intent) {
        // getPluginRef().call("onNewIntent");
        i.callActivityOnNewIntent(plugin, intent);
    }

}
