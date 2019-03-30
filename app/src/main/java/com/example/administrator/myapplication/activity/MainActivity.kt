package com.example.administrator.myapplication.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.KeyEvent
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.FragmentUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.fragment.EmptyFragment
import com.example.administrator.myapplication.fragment.OrderFragment
import com.example.administrator.myapplication.fragment.UserCenterFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    private val userCenterFragment = UserCenterFragment()
    private val orderFragment = OrderFragment()
    private val emptyFragment = EmptyFragment()

    private var key = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTransaction()
        initView()
        initData()
    }

    private fun initView() {
        llOrder.setOnClickListener {
            resetSelect()
            selectEmpty()
        }
        llOrder.setOnLongClickListener {
            if (key != 5) {
                key = 0;
                return@setOnLongClickListener true
            }
            resetSelect()
            selectOrder()
            return@setOnLongClickListener true
        }

        llUserCenter.setOnClickListener {
            resetSelect()
            selectUserCenter()
        }

        btGetOrder.setOnClickListener {
            ActivityUtils.startActivity(MyOrderActivity::class.java)
        }

    }

    private fun initData() {
        FragmentUtils.add(supportFragmentManager, orderFragment, R.id.fragmentContainer)
        FragmentUtils.add(supportFragmentManager, userCenterFragment, R.id.fragmentContainer)
        FragmentUtils.add(supportFragmentManager, emptyFragment, R.id.fragmentContainer)
        resetSelect()
        selectUserCenter()
    }

    //选择空
    private fun selectEmpty() {
        key++;
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.show(emptyFragment)
    }

    //选择订单
    private fun selectOrder() {
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.showHide(orderFragment)
    }

    //选择个人中心
    private fun selectUserCenter() {
        key = 0;
        ivUserCenter.setImageResource(R.mipmap.ic_user)
        tvUserCenter.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.showHide(userCenterFragment)
    }

    //重置选择
    private fun resetSelect() {
        ivOrder.setImageResource(R.mipmap.ic_home)
        tvOrder.setTextColor(resources.getColor(R.color.txt_gray))

        ivUserCenter.setImageResource(R.mipmap.ic_user)
        tvUserCenter.setTextColor(resources.getColor(R.color.txt_gray))


        FragmentUtils.hide(emptyFragment)
        FragmentUtils.hide(orderFragment)
        FragmentUtils.hide(userCenterFragment)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val home = Intent(Intent.ACTION_MAIN);
            home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

            //Fragment
            val slideTransition = Slide(Gravity.START)
            slideTransition.duration = 300
            emptyFragment.reenterTransition = slideTransition
            emptyFragment.enterTransition = slideTransition
            emptyFragment.exitTransition = slideTransition
            emptyFragment.sharedElementEnterTransition = ChangeBounds()

//            val explode = Explode()
//            explode.duration = 300
//            userCenterFragment.reenterTransition = explode
//            userCenterFragment.enterTransition = explode
//            userCenterFragment.exitTransition = explode
//            userCenterFragment.sharedElementEnterTransition = ChangeBounds()
        }
    }

}
