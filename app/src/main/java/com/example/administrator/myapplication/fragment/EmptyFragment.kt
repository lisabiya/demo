package com.example.administrator.myapplication.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseFragment
import com.example.administrator.myapplication.utils.AnimationUtil
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_empty.*


class EmptyFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_empty, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    var currentFrame = 0;

    override fun initView() {
        initLottie()
        btGetOrder.setOnClickListener {
            //            initDialog()
            lottie.playAnimation()
        }
        bt1.setOnClickListener {
            lottie.setAnimation("data15.json");
        }
        bt2.setOnClickListener {
            lottie.setAnimation("high_five.json");
        }
        bt3.setOnClickListener {
            lottie.setAnimation("page.json");
        }
        btNext.setOnClickListener {
            if (lottie.maxFrame <= currentFrame) {
                currentFrame = 0
            }
            lottie.frame = currentFrame++
        }
        ivChange.setOnClickListener {
            initDialog()
        }
    }

    private fun initLottie() {
        lottie.imageAssetsFolder = "images"
    }

    override fun initData() {
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onViewShow()
        }
    }

    private fun initDialog() {
        val bottomSheetDialog = BottomSheetDialog(activity!!)
        val dialogView = bottomSheetDialog.layoutInflater.inflate(R.layout.dialog_bottom, null)
        dialogView.findViewById<Button>(R.id.btOk).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        dialogView.findViewById<SimpleDraweeView>(R.id.roundView).setActualImageResource(R.mipmap.forgotme)
        bottomSheetDialog.setContentView(dialogView)
        (dialogView.parent as View).setBackgroundColor(Color.TRANSPARENT)
        //给布局设置透明背景色
        bottomSheetDialog.show()
        AnimationUtil.doAnimation(dialogView, AnimationUtil.AnimationType.ROTATION)
    }

}