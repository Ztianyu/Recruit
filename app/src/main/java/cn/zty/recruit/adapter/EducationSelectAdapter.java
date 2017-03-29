package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.EducationSelectListener;

/**
 * Created by zty on 2017/3/29.
 */

public class EducationSelectAdapter extends RecyclerAdapter<TipModel, ViewHolder> {

    EducationSelectListener listener;

    public EducationSelectAdapter(Context context, EducationSelectListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_education_select, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.textEducation, data.get(position).getValue());

        if (data.get(position).isSelected()) {
            holder.getView(R.id.imgEducation).setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_radio_select));
        } else {
            holder.getView(R.id.imgEducation).setBackground(ResourceUtil.resToDrawable(context, R.drawable.ic_radio_normal));
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEducationListener(data.get(position));
            }
        });
    }
}
