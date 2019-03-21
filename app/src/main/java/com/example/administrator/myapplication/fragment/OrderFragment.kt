package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.example.administrator.myapplication.OrderViewModel
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.adapter.InfoAdapter
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.bean.Info
import kotlinx.android.synthetic.main.fragment_orderr.*

class OrderFragment : BaseFragment() {

    interface OrderListener {
        //业务
        fun getInfo(): LiveData<Info>

        //动作监听
    }

    private lateinit var orderListener: OrderListener;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_orderr, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderListener = ViewModelProviders.of(this).get(OrderViewModel::class.java).listener
        initView()
        initData()
    }

    override fun initView() {
        add(10) {
            ToastUtils.showLong(it)
        }
    }

    override fun initData() {
        rvList.layoutManager = LinearLayoutManager(context)
        orderListener.getInfo().observe(this, Observer { info ->
            if (rvList.adapter == null) {
                val adapter = InfoAdapter(info.results.happy);
                rvList.adapter = adapter;
            } else {
                (rvList.adapter as InfoAdapter).data.clear()
                (rvList.adapter as InfoAdapter).data.addAll(info.results.happy)
            }
        })

    }

    private fun add(int: Int, string: (String) -> Unit) {
        string("" + int);
    }


}