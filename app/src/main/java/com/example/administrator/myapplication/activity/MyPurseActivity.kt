package com.example.administrator.myapplication.activity

import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.ConvertUtils
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_my_purse.*


class MyPurseActivity : BaseActivity(), View.OnClickListener {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarDarkFont(true).init()
        setContentView(R.layout.activity_my_purse)
        initTransaction()
        initView()
        initData()
    }

    private fun initView() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initData() {
        rlSystemInformation.setOnClickListener(this)
        rlVerified.setOnClickListener(this)
        rlMyOrder.setOnClickListener(this)
        rlMyPurse.setOnClickListener(this)
        rlSetting.setOnClickListener(this)
        rlRecord.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        reset(rlSystemInformation)
        reset(rlVerified)
        reset(rlMyOrder)
        reset(rlMyPurse)
        reset(rlSetting)
        reset(rlRecord)
        //start scene 是当前的scene
        TransitionManager.beginDelayedTransition(SceneRoot, TransitionInflater
                .from(this).inflateTransition(R.transition.trans))
        //next scene 此时通过代码已改变了scene statue
        changeScene(v);
    }

    private fun reset(view: View?) {
        val layoutParams = view!!.layoutParams
        layoutParams.height = primarySize
        view.layoutParams = layoutParams
    }

    private fun changeScene(view: View?) {
        changeSize(view)
        view!!.visibility = View.VISIBLE
    }

    private var primarySize = ConvertUtils.dp2px(46f);
    /**
     * view的宽高1.5倍和原尺寸大小切换 * 配合ChangeBounds实现缩放效果 * @param view
     */
    private fun changeSize(view: View?) {
        val layoutParams = view!!.layoutParams
        layoutParams.height = ((1.5 * primarySize).toInt())
        view.layoutParams = layoutParams
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initTransaction() {
        val slide = Slide(Gravity.START)
        slide.duration = 300
        window.enterTransition = slide

        val slide2 = Slide(Gravity.END)
        slide2.duration = 300
        window.returnTransition = slide2
    }
}