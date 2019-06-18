package com.example.administrator.myapplication.base;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MultiDex.install(this);
        Fresco.initialize(this);
    }

    public static BaseApplication getInstance() {
        return application;
    }
}
