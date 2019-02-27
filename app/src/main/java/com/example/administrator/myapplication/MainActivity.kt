package com.example.administrator.myapplication

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.administrator.myapplication.base.DocAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BarUtils.setNavBarColor(window, resources.getColor(R.color.cornflowerblue))
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }


    private fun initView() {
        btClick.setOnClickListener {
            ToastUtils.showShort("可以点击")
        }
        //list
        val mDate = ArrayList<String>()
        for (index in 1..100) {
            mDate.add("项目$index")
        }
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = DocAdapter(this, mDate, DocAdapter.OnItemClickListener {
            ToastUtils.showShort(mDate[it])
        })

    }

}
