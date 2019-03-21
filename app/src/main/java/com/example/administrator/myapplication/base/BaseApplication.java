package com.example.administrator.myapplication.base;

import android.app.Application;

import com.coloros.mcssdk.PushManager;
import com.coloros.mcssdk.callback.PushAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import androidx.multidex.MultiDex;

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MultiDex.install(this);
        Fresco.initialize(this);
        initOPPO();
    }

    public static BaseApplication getInstance() {
        return application;
    }


    private void initOPPO() {
        if (PushManager.isSupportPush(application)) {
            PushManager.getInstance().register(application, "", "", new PushAdapter() {
                @Override
                public void onRegister(int i, String s) {
                    super.onRegister(i, s);

                }
            });
        }
    }

}
