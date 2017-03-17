package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.ui.activity.learn.MajorDetailActivity;

/**
 * Created by zty on 2017/3/16.
 */

public class InstitutionMajorAdapter extends RecyclerAdapter<InstitutionMajorModel, ViewHolder> {

    public InstitutionMajorAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_institution_major, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.textInstitutionMajorTip, (position + 1) + "");

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MajorDetailActivity.class));
            }
        });

    }
}
