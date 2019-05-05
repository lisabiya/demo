package com.example.administrator.myapplication.adapter;

import android.graphics.drawable.Animatable;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Happiness;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.List;

public class InfoAdapter extends BaseQuickAdapter<Happiness.ResultsBean, BaseViewHolder> {


    public InfoAdapter(@Nullable List<Happiness.ResultsBean> data) {
        super(R.layout.adapter_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Happiness.ResultsBean item) {
        if (item == null) return;
        helper.setText(R.id.tvInfo, item.getDesc());

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.ivImage);
        if (item.getUrl() != null) {
            setControllerListener(simpleDraweeView, item.getUrl());
        }
    }

    private void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath) {
        ControllerListener<ImageInfo> controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) simpleDraweeView.getLayoutParams();
                int width = imageInfo.getWidth();
                int height = imageInfo.getHeight();
                layoutParams.dimensionRatio = width + ":" + height;
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener)
                .setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }

}
