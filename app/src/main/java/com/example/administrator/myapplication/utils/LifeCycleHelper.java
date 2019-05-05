package com.example.administrator.myapplication.utils;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Create by Administrator on 2019/4/24
 * 生命周期查看
 */
public class LifeCycleHelper implements LifecycleObserver {

    public LifeCycleHelper(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate() {
        Log.e("LifeCycleHelper", "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        Log.e("LifeCycleHelper", "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        Log.e("LifeCycleHelper", "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        Log.e("LifeCycleHelper", "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        Log.e("LifeCycleHelper", "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        Log.e("LifeCycleHelper", "onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onAny() {
        Log.e("LifeCycleHelper", "onAny");
    }
}
