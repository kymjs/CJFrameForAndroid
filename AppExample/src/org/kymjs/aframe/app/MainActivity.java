package org.kymjs.aframe.app;

import org.kymjs.aframe.plugin.activity.CJActivityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
    private String pluginPath;

    private Button mBtnPlugin;
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnPlugin = (Button) findViewById(R.id.plugin);
        mBtnPlugin.setOnClickListener(this);
        editText = (TextView) findViewById(R.id.editText1);

        pluginPath = Environment.getExternalStorageDirectory()
                + "/PluginExample.apk";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.plugin:
            // 没有数据交互时使用
            // CJActivityUtils.launchPlugin(this, 0, pluginPath);
            // 有数据交互时使用
            Intent intent = CJActivityUtils.getPluginIntent(this, 0,
                    pluginPath);
            Bundle extras = new Bundle();
            extras.putCharSequence("courier", editText.getText());
            intent.putExtras(extras);
            startActivity(intent);
            break;
        }
    }
}
