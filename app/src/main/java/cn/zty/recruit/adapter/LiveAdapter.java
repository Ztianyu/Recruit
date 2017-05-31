package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.LiveModel;
import cn.zty.recruit.listener.LiveItemListener;

/**
 * Created by zty on 2017/4/1.
 */

public class LiveAdapter extends RecyclerAdapter<LiveModel, ViewHolder> {

    LiveItemListener listener;

    public LiveAdapter(Context context, LiveItemListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_life, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        if (position == 0) {
            holder.getView(R.id.imgLive).setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_live1));
            holder.setText(R.id.textLiveTitle, "图书馆 --  自由阅读");
            holder.setText(R.id.textLiveAuthor, "教学达人");
            holder.setText(R.id.textLiveCommentCount, "37");
            holder.setText(R.id.textLiveTime, "3小时前");
        }
        if (position == 1) {
            holder.getView(R.id.imgLive).setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_live2));
            holder.setText(R.id.textLiveTitle, "外语教学 --  英语口语");
            holder.setText(R.id.textLiveAuthor, "教学达人");
            holder.setText(R.id.textLiveCommentCount, "224");
            holder.setText(R.id.textLiveTime, "3小时前");
        }
        if (position == 2) {
            holder.getView(R.id.imgLive).setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_live3));
            holder.setText(R.id.textLiveTitle, "英语从一本书开始");
            holder.setText(R.id.textLiveAuthor, "教学达人");
            holder.setText(R.id.textLiveCommentCount, "115");
            holder.setText(R.id.textLiveTime, "4小时前");
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLiveClick(position);
            }
        });

    }
}
