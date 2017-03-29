package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.base.Constants;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.textInstitutionMajorTip, (position + 1) + "");
        holder.setText(R.id.textMajorName, data.get(position).getName());
        holder.setText(R.id.textMajorIntroduction, data.get(position).getRemarks());
        holder.setText(R.id.textMajorIntroduction, data.get(position).getRemarks());
        holder.setText(R.id.textMajorPrise, data.get(position).getMoney() + "");

        String unit = "";
        if (data.get(position).getChargeStandard().equals("1")) {
            unit = Constants.chargeStandard1;
        }
        if (data.get(position).getChargeStandard().equals("2")) {
            unit = Constants.chargeStandard2;
        }
        holder.setText(R.id.textMajorUnit, "元/" + unit);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MajorDetailActivity.class)
                        .putExtra("model", data.get(position)));
            }
        });

    }
}
