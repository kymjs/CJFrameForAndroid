package org.kymjs.aframe.plugin.service;

import org.kymjs.aframe.plugin.MainActivity;
import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.example.R;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 作为插件Activity必须继承CJActivity
 */
public class ServiceActivity extends CJActivity {

    private Button mBtnService;
    private Button mBtnBind;

    private TestBindService serviceTwo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_service);
        mBtnService = (Button) that
                .findViewById(R.id.aty_service_btn);
        mBtnService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里的Intent必须是从CJServiceUtils中获取的
                that.startService(CJServiceUtils.getPluginIntent(
                        that, MainActivity.pluginPath,
                        TestService.class));
            }
        });
        mBtnBind = (Button) that.findViewById(R.id.aty_service_bind);
        mBtnBind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里的Intent必须是从CJServiceUtils中获取的
                Intent serviceIntent = CJServiceUtils
                        .getPluginIntent(that,
                                MainActivity.pluginPath,
                                TestBindService.class);
                that.bindService(serviceIntent, serviceConnection,
                        BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        that.unbindService(serviceConnection);
    }

    // 绑定服务 链接
    private ServiceConnection serviceConnection = new ServiceConnection() {
        // 绑定服务
        public void onServiceConnected(ComponentName name,
                IBinder service) {
            // 用于获取服务返回的数据信息 -- 此处获取ServiceTwo的对象
            serviceTwo = ((TestBindService.ServiceHolder) service)
                    .create();
            serviceTwo.show();
        }

        // 解绑服务
        public void onServiceDisconnected(ComponentName name) {
            Log.i("debug", "--" + name);
        }
    };
}
