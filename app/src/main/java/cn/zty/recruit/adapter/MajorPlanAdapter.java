package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.PlanModel;

/**
 * Created by zty on 2017/3/17.
 */

public class MajorPlanAdapter extends RecyclerAdapter<PlanModel, ViewHolder> {

    public MajorPlanAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_major_plan, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.textClassCount, position + "学时");
        holder.setText(R.id.textClassName, data.get(position).getName());
        holder.setText(R.id.textClassNum, data.get(position).getCodeNm());
        holder.setText(R.id.textClassCount, data.get(position).getHours() + "");
    }
}
