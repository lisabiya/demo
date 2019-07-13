package com.example.administrator.myapplication.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import com.example.administrator.myapplication.bean.Order
import com.example.administrator.myapplication.databinding.ActivityMyorderBinding
import com.example.administrator.myapplication.utils.AnimationUtil
import kotlinx.android.synthetic.main.activity_myorder.*


class MyOrderActivity : BaseActivity() {

    lateinit var binding: ActivityMyorderBinding
    var order = Order("标题")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myorder)
        initView()
        initData()
    }

    private fun initView() {
        iv_page2.setOnClickListener {
            order.title = "下一个标题"
            order.add = "文化是一个汉语词语，拼音是wén huà，英文是culture，文化是相对于经济、政治而言的人类全部精神活动及其产品。是非常广泛和最具人文意味的概念，简单来说文化就是地区人类的生活要素形态的统称：即衣、冠、文、物、食、住、行等。给文化下一个准确或精确的定义，的确是一件非常困难的事情。对文化这个概念的解读，人类也一直众说不一。但东西方的辞书或百科中却有一个较为共同的解释和理解：文化是相对于政治、经济而言的人类全部精神活动及其活动产品。"
            binding.order = order
        }
    }


    private fun initData() {
        binding.order = order
    }

    override fun onResume() {
        super.onResume()
        initAnimation()
    }

    private fun initAnimation() {
        iv_page2.post {
            val width = iv_page2.width
            AnimationUtil.doXAnimation(iv_page2, width)

            val width2 = iv_page1.width
            AnimationUtil.doXAnimation2(iv_page1, width2)

            AnimationUtil.doYAnimation2(iv_dodle)
        }
    }
}