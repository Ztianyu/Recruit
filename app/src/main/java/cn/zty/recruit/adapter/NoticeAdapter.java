package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.NoticeModel;

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
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
