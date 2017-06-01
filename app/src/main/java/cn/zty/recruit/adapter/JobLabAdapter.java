package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/6/1.
 */

public class JobLabAdapter extends RecyclerAdapter<TipModel, ViewHolder> {

    public JobLabAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_job_lab, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.textLab, data.get(position).getValue());
    }
}
