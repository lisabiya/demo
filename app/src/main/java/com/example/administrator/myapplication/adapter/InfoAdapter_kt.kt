package com.example.administrator.myapplication.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.bean.Happiness
import com.facebook.drawee.view.SimpleDraweeView

class InfoAdapter_kt(data: List<Happiness.ResultsBean>)
    : BaseQuickAdapter<Happiness.ResultsBean, BaseViewHolder>(R.layout.adapter_info, data) {

    override fun convert(helper: BaseViewHolder?, item: Happiness.ResultsBean?) {
        item?.run {
            helper?.setText(R.id.tvInfo, desc)
            val simpleDraweeView = helper?.getView<SimpleDraweeView>(R.id.ivImage)
            simpleDraweeView?.setImageURI(url)
        }
    }


}
