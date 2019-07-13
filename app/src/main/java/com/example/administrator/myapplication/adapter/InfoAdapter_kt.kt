package com.example.administrator.myapplication.adapter

import android.graphics.drawable.Animatable
import android.net.Uri
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.bean.Happiness
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

class InfoAdapter_kt(data: List<Happiness.ResultsBean>)
    : BaseQuickAdapter<Happiness.ResultsBean, BaseViewHolder>(R.layout.adapter_info, data) {

    override fun convert(helper: BaseViewHolder?, item: Happiness.ResultsBean?) {
        item?.run {
            helper?.setText(R.id.tvInfo, desc)
            val simpleDraweeView = helper?.getView<SimpleDraweeView>(R.id.ivImage)
            if (item.url != null) {//慎重打开
                setControllerListener(simpleDraweeView!!, item.url)
            }
        }
        item.run {

        }
    }

    private fun setControllerListener(simpleDraweeView: SimpleDraweeView, imagePath: String) {
        val controllerListener = object : BaseControllerListener<ImageInfo>() {
            override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, anim: Animatable?) {
                if (imageInfo == null) {
                    return
                }
                val layoutParams = simpleDraweeView.layoutParams as ConstraintLayout.LayoutParams
                val width = imageInfo.width
                val height = imageInfo.height
                layoutParams.dimensionRatio = "$width:$height"
                simpleDraweeView.layoutParams = layoutParams
            }

            override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {

            }

            override fun onFailure(id: String?, throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
        val controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener)
                .setUri(Uri.parse(imagePath)).build()
        simpleDraweeView.controller = controller
    }
}
