package com.example.administrator.myapplication.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.FragmentUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.fragment.OrderFragment
import com.example.administrator.myapplication.fragment.UserCenterFragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    private val userCenterFragment = UserCenterFragment()
    private val orderFragment = OrderFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {
        llOrder.setOnClickListener {
            resetSelect()
            selectOrder()
        }
        llUserCenter.setOnClickListener {
            resetSelect()
            selectUserCenter()
        }

        btGetOrder.setOnClickListener {
            ActivityUtils.startActivity(SystemInfoActivity::class.java)
        }

    }

    private fun initData() {
        selectUserCenter()
    }

    //选择订单
    private fun selectOrder() {
        ivOrder.setImageResource(R.mipmap.ic_blue_anchor)
        tvOrder.setTextColor(resources.getColor(R.color.colorPrimary))

        FragmentUtils.replace(supportFragmentManager, orderFragment, R.id.fragmentContainer)
    }

    //选择个人中心
    private fun selectUserCenter() {
        ivUserCenter.setImageResource(R.mipmap.ic_portrait)
        tvUserCenter.setTextColor(resources.getColor(R.color.colorPrimary))

        FragmentUtils.replace(supportFragmentManager, userCenterFragment, R.id.fragmentContainer)
    }

    //重置选择
    private fun resetSelect() {
        ivOrder.setImageResource(R.mipmap.ic_blue_anchor)
        tvOrder.setTextColor(resources.getColor(R.color.txt_gray))

        ivUserCenter.setImageResource(R.mipmap.ic_portrait)
        tvUserCenter.setTextColor(resources.getColor(R.color.txt_gray))
    }

}
