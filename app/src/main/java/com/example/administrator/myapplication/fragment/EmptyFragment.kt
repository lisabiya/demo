package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseFragment

class EmptyFragment : BaseFragment() {
    override fun initData() {
    }

    override fun initView() {
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false)
        return rootView
    }


}