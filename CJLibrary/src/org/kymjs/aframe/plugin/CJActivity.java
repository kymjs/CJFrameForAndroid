/*
 * Copyright (c) 2014, CJFrameForAndroid 张涛 (kymjs123@gmail.com).
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
package org.kymjs.aframe.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

/**
 * 插件Activity的基类，若要使用CJFrame，必须使用本基类作为BaseActivity<br>
 * 
 * <b>注意</b> 在CJActivity以及子类中，绝对不可以使用this调用，应该使用that调用<br>
 * <b>创建时间</b> 2014-10-11 <br>
 * 
 * @author kymjs(kymjs123@gmail.com)
 * @version 1.0
 */
public abstract class CJActivity extends Activity implements I_CJActivity {
    /**
     * that指针指向的是当前插件的Context（由于是插件化开发，this指针绝对不能使用）
     */
    protected Activity that; // 替代this指针
    protected String mDexPath;
    protected int mFrom = CJConfig.FROM_PLUGIN;

    /**
     * 设置托管Activity，并将that指针指向那个托管的Activity
     */
    @Override
    public void setProxy(Activity proxyActivity, String dexPath) {
        that = proxyActivity;
        mDexPath = dexPath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFrom = savedInstanceState.getInt(CJConfig.FROM,
                    CJConfig.FROM_PLUGIN);
        }
        // 如果不是来自托管类，则将that指针指向自身
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onCreate(savedInstanceState);
            that = this;
        }
    }

    /**
     * 如果你想从插件Activity跳转到app的Activity，你不能使用startActivity()
     */
    protected void startActivityByApp(Class<?> cls) {
        startActivityByApp(cls.getName());
    }

    /**
     * 如果你想从插件Activity跳转到app的Activity，你不能使用startActivity()
     */
    protected void startActivityForResultByApp(Class<?> cls, int requestCode) {
        startActivityForResultByApp(cls.getName(), requestCode);
    }

    /**
     * 如果你想从插件Activity跳转到app的Activity，你不能使用startActivity()
     */
    protected void startActivityByApp(String className) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            Intent intent = new Intent();
            intent.setClassName(this, className);
            that.startActivity(intent);
        } else {
            Intent intent = new Intent(CJTool.getProxyViewAction(className,
                    getClassLoader()));
            intent.putExtra(CJConfig.KEY_DEX_PATH, mDexPath);
            intent.putExtra(CJConfig.KEY_EXTRA_CLASS, className);
            that.startActivity(intent);
        }
    }

    /**
     * 如果你想从插件Activity跳转到app的Activity，你不能使用startActivityForResult(String,int)
     */
    public void startActivityForResultByApp(String className, int requestCode) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            Intent intent = new Intent();
            intent.setClassName(this, className);
            that.startActivityForResult(intent, requestCode);
        } else {
            Intent intent = new Intent(CJTool.getProxyViewAction(className,
                    getClassLoader()));
            intent.putExtra(CJConfig.KEY_DEX_PATH, mDexPath);
            intent.putExtra(CJConfig.KEY_EXTRA_CLASS, className);
            that.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void setContentView(View view) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.setContentView(view, params);
        } else {
            that.setContentView(view, params);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }

    @Override
    public void addContentView(View view, LayoutParams params) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.addContentView(view, params);
        } else {
            that.addContentView(view, params);
        }
    }

    @Override
    public View findViewById(int id) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getIntent();
        } else {
            return that.getIntent();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @Override
    public Resources getResources() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getResources();
        } else {
            return that.getResources();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getSharedPreferences(name, mode);
        } else {
            return that.getSharedPreferences(name, mode);
        }
    }

    @Override
    public Context getApplicationContext() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getApplicationContext();
        } else {
            return that.getApplicationContext();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public Window getWindow() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.getSystemService(name);
        } else {
            return that.getSystemService(name);
        }
    }

    @Override
    public void finish() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.finish();
        } else {
            that.finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onStart() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onRestart();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void onResume() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            return super.onKeyUp(keyCode, event);
        }
        return false;
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onWindowAttributesChanged(params);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mFrom == CJConfig.FROM_PLUGIN) {
            super.onWindowFocusChanged(hasFocus);
        }
    }
}
