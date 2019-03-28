package com.example.administrator.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.administrator.myapplication.OrderViewModel
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.adapter.InfoAdapter
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.bean.Happiness
import com.example.administrator.myapplication.bean.Info
import kotlinx.android.synthetic.main.fragment_orderr.*
import java.util.*

class OrderFragment : BaseFragment() {

    interface OrderListener {
        //业务
        fun getInfo(): LiveData<Info>

        fun getHappiness(): LiveData<Happiness>

        //动作监听
        fun onRefresh()
    }

    private lateinit var orderListener: OrderListener

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
    }

    override fun initData() {
        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        orderListener.getHappiness().observe(this, Observer { happiness ->
            val list = ArrayList<Happiness.ResultsBean>()
            list.addAll(happiness.results)
            if (rvList.adapter == null) {
                val adapter = InfoAdapter(list)
                rvList.adapter = adapter
            } else {
                (rvList.adapter as InfoAdapter).data.clear()
                (rvList.adapter as InfoAdapter).data.addAll(list)
                (rvList.adapter as InfoAdapter).notifyDataSetChanged()
            }
        })

        refresh.setOnClickListener {
            orderListener.onRefresh()
        }

    }


}