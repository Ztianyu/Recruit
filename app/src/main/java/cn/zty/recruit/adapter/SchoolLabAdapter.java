package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/3/18.
 */

public class SchoolLabAdapter extends RecyclerAdapter<TipModel, ViewHolder> {

    private boolean isOnly;

    public SchoolLabAdapter(Context context, boolean isOnly) {
        super(context);
        this.isOnly = isOnly;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.view_school_lab, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(R.id.textLab, data.get(position).getValue());

        int resId = 0;
        if ((position + 1) % 3 == 1) {
            resId = R.drawable.shape_school_lab1;
        } else if ((position + 1) % 3 == 2) {
            resId = R.drawable.shape_school_lab2;
        } else if ((position + 1) % 3 == 0) {
            resId = R.drawable.shape_school_lab3;
        }

        if (isOnly)
            resId = R.drawable.shape_school_lab4;


        holder.setBgRes(R.id.textLab, resId);
    }
}
