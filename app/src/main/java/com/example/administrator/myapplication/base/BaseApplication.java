package com.example.administrator.myapplication.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static BaseApplication getInstance() {
        return application;
    }


}
