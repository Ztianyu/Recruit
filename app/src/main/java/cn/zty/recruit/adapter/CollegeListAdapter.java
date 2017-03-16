package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.ui.activity.school.MajorListActivity;

/**
 * Created by zty on 2017/3/15.
 */

public class CollegeListAdapter extends RecyclerAdapter<CollegeModel, ViewHolder> {
    public CollegeListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_college_list, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.textCollegeListName, "学院" + (position + 1));
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MajorListActivity.class));
            }
        });
    }
}
