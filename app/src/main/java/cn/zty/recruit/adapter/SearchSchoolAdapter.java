package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.ui.activity.school.SchoolDetailActivity;

/**
 * Created by zty on 2017/3/28.
 */

public class SearchSchoolAdapter extends RecyclerAdapter<VocationalModel, ViewHolder> {

    public SearchSchoolAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_search, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.textSearchSchool, data.get(position).getName());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SchoolDetailActivity.class)
                        .putExtra("schoolId", data.get(position).getId()));
            }
        });
    }
}
