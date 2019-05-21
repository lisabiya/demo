package com.example.administrator.myapplication.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Happiness;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class InfoAdapter extends BaseQuickAdapter<Happiness.ResultsBean, BaseViewHolder> {


    public InfoAdapter(@Nullable List<Happiness.ResultsBean> data) {
        super(R.layout.adapter_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Happiness.ResultsBean item) {
        if (item == null) return;
        helper.setText(R.id.tvInfo, helper.getAdapterPosition() + 1 + "###" + item.getDesc());

        helper.setText(R.id.tvTime, item.getPublishedAt());

        SimpleDraweeView simpleDraweeView = helper.getView(R.id.ivImage);
        simpleDraweeView.setImageURI(item.getUrl());
    }

}
