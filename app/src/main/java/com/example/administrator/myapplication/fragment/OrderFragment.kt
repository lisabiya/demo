package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseFragment

class OrderFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_orderr, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun initView() {
        add(10) {
            ToastUtils.showLong(it)
        }
    }

    override fun initData() {

    }

    private fun add(int: Int, string: (String) -> Unit) {
        string("" + int);
    }


}