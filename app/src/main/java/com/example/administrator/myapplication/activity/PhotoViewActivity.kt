package com.example.administrator.myapplication.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.administrator.myapplication.R
import com.example.administrator.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoViewActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
//        linearLayout {
//            photo = PhotoView(this@PhotoViewActivity, null, 0)
//                    .lparams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
////            photo = imageView(R.mipmap.ic_home).lparams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        }
        initData()
    }

    private fun initData() {
        val url = intent.getStringExtra("url");
        url?.let {
            Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    LogUtils.e(e?.message)
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    LogUtils.e(resource)
                    runOnUiThread {
                        photo.setImageDrawable(resource)
                    }
                    return true
                }

            }).submit()
        }
    }
}