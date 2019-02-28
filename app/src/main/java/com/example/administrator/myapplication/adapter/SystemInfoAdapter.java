package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;


import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by debby on 2018/8/3.
 * 描述:
 */

public class SystemInfoAdapter extends RecyclerView.Adapter<SystemInfoAdapter.ViewHolder> {
    private Context context;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public SystemInfoAdapter(Context context, List<String> mData, OnItemClickListener onItemClickListener) {
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
        holder.tvInfo.setText(mData.get(position));

        holder.tvTime.setText(String.format(Locale.CHINA,
                "今天 10:0%d", position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfo;
        TextView tvTime;

        ViewHolder(View rootView) {
            super(rootView);
            tvInfo = rootView.findViewById(R.id.tvInfo);
            tvTime = rootView.findViewById(R.id.tvTime);
        }
    }
}
