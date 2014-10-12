package org.kymjs.aframe.plugin.service;

import org.kymjs.aframe.plugin.activity.CJActivity;
import org.kymjs.aframe.plugin.example.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActivity extends CJActivity {

    private Button mBtnService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.aty_service);
        mBtnService = (Button) that.findViewById(R.id.aty_service_btn);
        mBtnService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                that.startService(new Intent(that, TestService.class));
            }
        });
    }

}
