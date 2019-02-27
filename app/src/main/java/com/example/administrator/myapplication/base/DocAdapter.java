package com.example.administrator.myapplication.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.example.administrator.myapplication.R;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by debby on 2018/8/3.
 * 描述:
 */

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {
    private Context context;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public DocAdapter(Context context, List<String> mData, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.adapter_view, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_name.setText(mData.get(position));

        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        //
        if (position == 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.topMargin = SizeUtils.dp2px(600f);
            holder.itemView.setLayoutParams(layoutParams);
        } else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.topMargin = SizeUtils.dp2px(1f);
            holder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_cancel;

        ViewHolder(View rootView) {
            super(rootView);
            tv_name = rootView.findViewById(R.id.tv_name);
            tv_cancel = rootView.findViewById(R.id.tv_cancel);
        }
    }
}
