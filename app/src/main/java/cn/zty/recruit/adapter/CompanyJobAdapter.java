package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.CompanyJobModel;
import cn.zty.recruit.ui.activity.job.JobDetailActivity;

/**
 * Created by zty on 2017/6/1.
 */

public class CompanyJobAdapter extends RecyclerAdapter<CompanyJobModel, ViewHolder> {

    public CompanyJobAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_company_job, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.itemJobName, data.get(position).getName());
        holder.setText(R.id.itemJobType, data.get(position).getJobNatureLabel());
        holder.setText(R.id.itemJobDate, data.get(position).getCreateDate().substring(5, 10));
        holder.setText(R.id.itemJobCompany, data.get(position).getCompanyNm());
        holder.setText(R.id.itemSchoolPosition, data.get(position).getAreaNm());
        holder.setText(R.id.itemJobEdu, data.get(position).getEducationLabel());
        holder.setText(R.id.itemJobSalary, data.get(position).getSalaryLabel());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, JobDetailActivity.class).putExtra("model", data.get(position)));
            }
        });
    }

}
