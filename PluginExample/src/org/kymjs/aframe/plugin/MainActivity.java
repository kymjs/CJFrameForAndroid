package org.kymjs.aframe.plugin;

import org.kymjs.aframe.plugin.example.R;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends CJActivity implements OnClickListener {
    private String pluginPath;
    private Button mBtnFrag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_main);
        mBtnFrag = (Button) that.findViewById(R.id.button1);
        mBtnFrag.setOnClickListener(this);
        pluginPath = Environment.getExternalStorageDirectory()
                + "/PluginExample.apk";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
            CJActivityUtils.launchPlugin(that, pluginPath, FragmentAty.class);
            break;
        }
    }
}
