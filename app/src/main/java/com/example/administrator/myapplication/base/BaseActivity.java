package com.example.administrator.myapplication.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.activity.ViewInterface;
import com.gyf.barlibrary.ImmersionBar;

import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements ViewInterface {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getWindow().getDecorView().setBackground(ContextCompat.getDrawable(this, R.drawable.border_shadow));
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).transparentStatusBar().fitsSystemWindows(false).statusBarDarkFont(false).init();
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
