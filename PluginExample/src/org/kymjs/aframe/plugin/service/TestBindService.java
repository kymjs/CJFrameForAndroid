package org.kymjs.aframe.plugin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class TestBindService extends Service {

    public class ServiceHolder extends Binder {
        public TestBindService create() {
            return TestBindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceHolder();
    }
}
