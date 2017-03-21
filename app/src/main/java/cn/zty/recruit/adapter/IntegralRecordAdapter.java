package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.IntegralRecordModel;

/**
 * Created by zty on 2017/3/21.
 */

public class IntegralRecordAdapter extends RecyclerAdapter<IntegralRecordModel, ViewHolder> {

    public IntegralRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_integral_record, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
