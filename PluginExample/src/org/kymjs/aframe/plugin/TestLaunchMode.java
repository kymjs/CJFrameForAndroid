package org.kymjs.aframe.plugin;

import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.activity.CJActivityUtils;
import org.kymjs.aframe.plugin.example.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestLaunchMode extends CJActivity {
    public TestLaunchMode() {
        setLunchMode(LaunchMode.SINGLETOP);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_launch_test);
        ((TextView) that.findViewById(R.id.launch_text))
                .setText("当前启动模式为singleTop，你可以在代码中修改");
        Button btn = (Button) that.findViewById(R.id.launcu_btn);
        btn.setText("点击再次跳转到本Activity");
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CJActivityUtils.launchPlugin(that, MainActivity.pluginPath,
                        TestLaunchMode.class);
            }
        });
    }
}
