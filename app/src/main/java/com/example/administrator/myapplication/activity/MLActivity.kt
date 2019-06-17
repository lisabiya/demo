package com.example.administrator.myapplication.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.bumptech.glide.Glide
import com.example.administrator.myapplication.utils.GlideEngine
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.gyf.barlibrary.ImmersionBar
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


/**
 * Create by wkx on 2019/6/15
 *
 */
class MLActivity : AppCompatActivity() {

    lateinit var image: ImageView
    lateinit var text: Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarDarkFont(true).init()

        relativeLayout {
            //选择图片
            text = button("选择图片(目前只支持英文)") {
                id = com.example.administrator.myapplication.R.id.text
                textColor = resources.getColor(com.example.administrator.myapplication.R.color.colorPrimary)
                gravity = Gravity.CENTER

                onClick {
                    selectImage()
                }
            }.lparams {
                centerInParent()
                margin = 20
            }
            //预览图片
            image = imageView(com.example.administrator.myapplication.R.mipmap.forgotme).lparams {
                centerHorizontally()
                above(text)
            }
            scrollView {
                linearLayout {
                    textView("结果") {
                        textColor = resources.getColor(com.example.administrator.myapplication.R.color.colorPrimary)
                        textSize = 30f
                        tvResult = this;
                    }
                }
            }.lparams {
                below(text)
                centerHorizontally()
            }
            lparams(-1, -1)
        }
    }


    private fun selectImage() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        Matisse.from(this@MLActivity)
                                .choose(MimeType.ofImage())
                                .countable(true)
                                .maxSelectable(1)
                                .imageEngine(GlideEngine())
                                .forResult(10086);
                    }

                    override fun onDenied() {

                    }

                }).request()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10086 && resultCode == Activity.RESULT_OK) {
            val mSelected = Matisse.obtainResult(data!!)
            Glide.with(this).load(mSelected[0]).into(image)
            ml(mSelected[0])
        }
    }

    private fun ml(uri: Uri) {
        val image = FirebaseVisionImage.fromFilePath(this, uri)
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
        val result = detector.processImage(image)
                .addOnSuccessListener { findText ->
                    val build = StringBuilder()
                    val resultText = findText.text
                    for (block in findText.textBlocks) {
                        for (line in block.lines) {
                            for (element in line.elements) {
                                val elementText = element.text
                                build.append(elementText).append("\n")
                            }
                        }
                    }
                    tvResult.text = build.toString()
                }
                .addOnFailureListener {
                    tvResult.text = it.message
                }
    }

    private fun ml2(uri: Uri) {
        val languageIdentifier = FirebaseNaturalLanguage.getInstance().languageIdentification
        languageIdentifier.identifyLanguage("")
                .addOnSuccessListener { languageCode ->
                    if (languageCode !== "und") {

                    } else {
                    }
                    tvResult.text = languageCode
                }
                .addOnFailureListener(object : OnFailureListener {
                    override fun onFailure(@NonNull e: Exception) {
                        tvResult.text = e.message
                    }
                })

    }
}