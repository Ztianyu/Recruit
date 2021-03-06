package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.ui.activity.learn.InstitutionDetailActivity;

/**
 * Created by zty on 2017/3/16.
 */

public class TrainAdapter extends RecyclerAdapter<TrainingModel, ViewHolder> {
    public TrainAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_training, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setImage(context, R.id.itemSchoolImg, data.get(position).getImgUrl());
        holder.setText(R.id.textTrainingName, data.get(position).getName());
        holder.setText(R.id.itemTrainingPosition, data.get(position).getAreaNm());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, InstitutionDetailActivity.class)
                        .putExtra("orgId", data.get(position).getId()));
            }
        });
    }
}
