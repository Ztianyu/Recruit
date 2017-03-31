package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.TimeUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.NoticeModel;
import cn.zty.recruit.ui.activity.person.NoticeDetailActivity;

/**
 * Created by zty on 2017/3/21.
 */

public class NoticeAdapter extends RecyclerAdapter<NoticeModel, ViewHolder> {
    public NoticeAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_notice, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.textNoticeTitle, data.get(position).getTitle());
        holder.setText(R.id.textNoticeTime, TimeUtils.changeTime(data.get(position).getUpdateDate()));

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NoticeDetailActivity.class)
                        .putExtra("noticeModel", data.get(position)));
            }
        });
    }
}
