package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.UniversityModel;

/**
 * Created by zty on 2017/3/14.
 */

public class UniversityAdapter extends RecyclerAdapter<UniversityModel, ViewHolder> {

    public UniversityAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_school, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.itemSchoolTip, (position + 1) + "");
        holder.setText(R.id.textSchoolName, (position + 1) + "学院");
    }
}
