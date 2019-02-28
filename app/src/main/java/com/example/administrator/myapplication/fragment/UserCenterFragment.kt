package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_center.*

class UserCenterFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_user_center, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun initView() {

    }

    override fun initData() {
        portrait.setActualImageResource(R.drawable.ic_my)
        tvName.text = "正经的人"
    }


}