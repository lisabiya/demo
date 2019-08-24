package com.example.administrator.myapplication.activity

import android.os.Bundle
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import io.flutter.facade.FlutterFragment


class MyOrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myorder)
        supportFragmentManager.beginTransaction().add(R.id.fl_container, FlutterFragment()).commit()
    }

}