package org.kymjs.aframe.plugin;

import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.activity.CJActivityUtils;
import org.kymjs.aframe.plugin.example.R;
import org.kymjs.aframe.plugin.service.ServiceActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends CJActivity implements OnClickListener {
    private String pluginPath;
    private Button mBtnFrag;
    private Button mBtnService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_main);
        mBtnFrag = (Button) that.findViewById(R.id.button1);
        mBtnFrag.setOnClickListener(this);
        mBtnService = (Button) that.findViewById(R.id.button2);
        mBtnService.setOnClickListener(this);
        // 当做为插件运行时使用apk地址
        pluginPath = Environment.getExternalStorageDirectory()
                + "/PluginExample.apk";

        // 当做为APP单独运行时使用CJConfig.DEF_STR
        // pluginPath = CJConfig.DEF_STR;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
            CJActivityUtils.launchPlugin(that, pluginPath, FragmentAty.class);
            break;
        case R.id.button2:
            CJActivityUtils.launchPlugin(that, pluginPath,
                    ServiceActivity.class);
            break;
        }
    }
}
