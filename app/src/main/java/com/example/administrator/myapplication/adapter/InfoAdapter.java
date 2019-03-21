package com.example.administrator.myapplication.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.Info;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import androidx.annotation.Nullable;

public class InfoAdapter extends BaseQuickAdapter<Info.ResultsBean.HappyBean, BaseViewHolder> {


    public InfoAdapter(@Nullable List<Info.ResultsBean.HappyBean> data) {
        super(R.layout.adapter_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Info.ResultsBean.HappyBean item) {
        if (item == null) return;
        helper.setText(R.id.tvInfo, item.getDesc());
        SimpleDraweeView v = helper.getView(R.id.ivImage);
        if (item.getUrl() != null) {
            v.setImageURI(item.getUrl());
        }

    }


}
