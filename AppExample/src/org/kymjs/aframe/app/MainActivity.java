package org.kymjs.aframe.app;

import org.kymjs.aframe.plugin.CJActivityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private String pluginPath;

    private Button mBtnPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnPlugin = (Button) findViewById(R.id.plugin);
        mBtnPlugin.setOnClickListener(this);

        pluginPath = Environment.getExternalStorageDirectory()
                + "/PluginExample.apk";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.plugin:
            CJActivityUtils.launchPlugin(this, 0, pluginPath);
            break;
        }
    }
}
