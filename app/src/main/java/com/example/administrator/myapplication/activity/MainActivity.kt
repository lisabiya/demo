package com.example.administrator.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
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
        initView()
        initData()
        PermissionUtils.permission(PermissionConstants.STORAGE).callback(object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                ToastUtils.showLong("同意")
            }

            override fun onDenied() {
                ToastUtils.showLong("拒绝")
            }

        })
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
        ivOrder.setImageResource(R.mipmap.ic_blue_anchor)
        tvOrder.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.show(emptyFragment)
    }

    //选择订单
    private fun selectOrder() {
        ivOrder.setImageResource(R.mipmap.ic_blue_anchor)
        tvOrder.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.showHide(orderFragment)
    }

    //选择个人中心
    private fun selectUserCenter() {
        key = 0;
        ivUserCenter.setImageResource(R.mipmap.ic_portrait)
        tvUserCenter.setTextColor(resources.getColor(R.color.colorPrimary))
        FragmentUtils.showHide(userCenterFragment)
    }

    //重置选择
    private fun resetSelect() {
        ivOrder.setImageResource(R.mipmap.ic_blue_anchor)
        tvOrder.setTextColor(resources.getColor(R.color.txt_gray))

        ivUserCenter.setImageResource(R.mipmap.ic_portrait)
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
}
