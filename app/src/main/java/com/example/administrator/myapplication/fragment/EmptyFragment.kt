package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.map.MapViewManager
import kotlinx.android.synthetic.main.activity_my_order.*

class EmptyFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aMap.onCreate(savedInstanceState)
        initView()
        initData()
    }

    override fun initView() {
    }

    override fun initData() {
        val manager = MapViewManager(aMap)
        lifecycle.addObserver(manager)
    }


}