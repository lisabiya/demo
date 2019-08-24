package com.example.administrator.myapplication.utils;

import android.app.Activity;

import com.guideview.GuideView;
import com.guideview.GuideViewHelper;
import com.guideview.LightType;

/**
 * Create by wkx on 2019/7/30
 */
public class GuideHelper {
    private void showGuide(Activity activity) {
        new GuideViewHelper(activity)
//                .addView()
                .type(LightType.Rectangle)
                .autoNext()
                .onDismiss(new GuideView.OnDismissListener() {
                    @Override
                    public void dismiss() {

                    }
                })
                .show();
    }
}
