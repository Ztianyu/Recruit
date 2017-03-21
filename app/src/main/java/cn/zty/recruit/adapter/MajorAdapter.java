package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.ui.activity.school.MajorSchoolActivity;

/**
 * Created by zty on 2017/3/14.
 */

public class MajorAdapter extends RecyclerAdapter<MajorModel, ViewHolder> {

    public MajorAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_hot_major, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.itemMajorTip, (position + 1) + "");
//        holder.setText(R.id.itemMajorName, (position + 1) + "专业");

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MajorSchoolActivity.class));
            }
        });
    }
}
