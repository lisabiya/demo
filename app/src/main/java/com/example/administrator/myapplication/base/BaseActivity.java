package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.administrator.myapplication.activity.ViewInterface;
import com.gyf.barlibrary.ImmersionBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements ViewInterface {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        }
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentStatusBar().statusBarDarkFont(false);
        AdaptScreenUtils.adaptWidth(getResources(), 400);
    }


    @Override
    protected void onDestroy() {
        ImmersionBar.with(this).destroy();
        super.onDestroy();
    }

    @Override
    public void onNext(View view) {
        onFragmentViewCreated(view);
    }

    protected void onFragmentViewCreated(View view) {
        
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }


}
