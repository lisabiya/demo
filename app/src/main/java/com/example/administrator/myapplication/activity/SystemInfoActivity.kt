package com.example.administrator.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.administrator.myapplication.OrderViewModel
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.adapter.InfoAdapter_kt
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.bean.Happiness
import kotlinx.android.synthetic.main.activity_system_info.*

class SystemInfoActivity : BaseActivity() {
    private lateinit var orderViewModel: OrderViewModel
    private var page = 1;
    private val list = ArrayList<Happiness.ResultsBean>()
    private val adapter = InfoAdapter_kt(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_info)
        initView()
        initData()
    }

    private fun initView() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initData() {
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        refresh.setOnRefreshListener {
            page = 1
            getList(page)
        }
        refresh.setOnLoadMoreListener {
            page++
            getList(page)
        }
//        rvSysInfo.layoutManager = LinearLayoutManager(this)
        rvSysInfo.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvSysInfo.adapter = adapter
        rvSysInfo.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val intent = Intent(this@SystemInfoActivity, PhotoViewActivity::class.java)
                intent.putExtra("url", list[position].url)
                startActivity(intent)
            }
        })
        observeData()
        getList(page)
    }

    private fun observeData() {
        orderViewModel.getHappiness(page, lifecycle).observe(this, Observer { happiness ->
            refresh.finishLoadMore()
            refresh.finishRefresh()

            LogUtils.e("page=$page happiness=${happiness.results.size}")

            if (happiness != null) {
                if (page == 1) {
                    list.clear()
                }
                list.addAll(happiness.results)
                adapter.notifyDataSetChanged()
            }

        })
    }

    private fun getList(page: Int) {
        orderViewModel.getHappinessWeb(page, lifecycle)
    }

}