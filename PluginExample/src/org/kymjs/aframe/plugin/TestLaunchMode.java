package org.kymjs.aframe.plugin;

import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.activity.CJActivityUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestLaunchMode extends CJActivity {
    public TestLaunchMode() {
        setLunchMode(LaunchMode.SINGLETASK);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(that);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CJActivityUtils.launchPlugin(that,
                        MainActivity.pluginPath, MainActivity.class);
            }
        });
        setContentView(btn);
    }
}
