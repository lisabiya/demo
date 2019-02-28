package com.example.administrator.myapplication.base;

import android.os.Bundle;


import com.blankj.utilcode.util.AdaptScreenUtils;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdaptScreenUtils.adaptWidth(getResources(), 375);

    }

}
