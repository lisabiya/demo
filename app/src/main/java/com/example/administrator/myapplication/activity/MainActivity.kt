package com.example.administrator.myapplication.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.FragmentUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.fragment.EmptyFragment
import com.example.administrator.myapplication.fragment.OrderFragment
import com.example.administrator.myapplication.fragment.UserCenterFragment
import com.example.administrator.myapplication.net.core.callback.DisposableManager
import com.example.administrator.myapplication.utils.AnimationUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onFragmentViewCreated(view: View?) {
        AnimationUtil.doAnimation(view, AnimationUtil.AnimationType.PRESS)
    }


    private val userCenterFragment = UserCenterFragment()
    private val orderFragment = OrderFragment()
    private val emptyFragment = EmptyFragment()
    private var key = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTransaction()
        initView()
        initData()
    }

    private fun initView() {
        llOrder.setOnClickListener {
            key++
            if (emptyFragment.isVisible) {
                return@setOnClickListener
            }
            resetSelect()
            selectEmpty()
        }
        llOrder.setOnLongClickListener {
            if (key == 6) {
                key = 0
                startActivity(Intent(this, SystemInfoActivity::class.java))
                return@setOnLongClickListener true
            }
            if (key != 5) {
                key = 0
                return@setOnLongClickListener true
            }
            if (orderFragment.isVisible) {
                return@setOnLongClickListener true
            }
            resetSelect()
            selectOrder()
            return@setOnLongClickListener true
        }

        llUserCenter.setOnClickListener {
            key = 0
            if (userCenterFragment.isVisible) {
                return@setOnClickListener
            }
            resetSelect()
            selectUserCenter()
        }

        btGetOrder.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                startActivity(Intent(this, MapActivity::class.java))
            }
        }

    }

    private fun initData() {
        FragmentUtils.add(supportFragmentManager, userCenterFragment, R.id.fragmentContainer)
        FragmentUtils.add(supportFragmentManager, emptyFragment, R.id.fragmentContainer)
        FragmentUtils.add(supportFragmentManager, orderFragment, R.id.fragmentContainer)
        userCenterFragment.subscribe(this)
        emptyFragment.subscribe(this)
        orderFragment.subscribe(this)

        resetSelect()
        selectUserCenter()
    }

    //选择空
    private fun selectEmpty() {
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            emptyFragment.enterTransition = Slide(Gravity.START)
        }
        FragmentUtils.show(emptyFragment)
    }

    //选择订单
    private fun selectOrder() {
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        FragmentUtils.show(orderFragment)
    }

    //选择个人中心
    private fun selectUserCenter() {
        ivUserCenter.setImageResource(R.mipmap.ic_user)
        tvUserCenter.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            userCenterFragment.enterTransition = Explode()
        }
        FragmentUtils.show(userCenterFragment)
    }

    //重置选择
    private fun resetSelect() {
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(ContextCompat.getColor(this, R.color.txt_gray))
        ivUserCenter.setImageResource(R.mipmap.ic_user)
        tvUserCenter.setTextColor(ContextCompat.getColor(this, R.color.txt_gray))

        FragmentUtils.hide(emptyFragment)
        FragmentUtils.hide(orderFragment)
        FragmentUtils.hide(userCenterFragment)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val home = Intent(Intent.ACTION_MAIN)
            home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            home.addCategory(Intent.CATEGORY_HOME)
            startActivity(home)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        DisposableManager.onDestroy()
        super.onDestroy()
    }

    private fun initTransaction() {
        //设置进入与退出效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //activity
            val slide = Explode()
            slide.duration = 300
            window.exitTransition = slide

            val slide2 = Explode()
            slide2.duration = 300
            window.reenterTransition = slide2
        }
    }

}
