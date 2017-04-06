package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.ui.activity.school.SchoolDetailActivity;
import cn.zty.recruit.utils.StringUtils;

/**
 * Created by zty on 2017/3/14.
 */

public class UniversityAdapter extends RecyclerAdapter<VocationalModel, ViewHolder> {

    private boolean isHaveTip;

    public UniversityAdapter(Context context, boolean isHaveTip) {
        super(context);
        this.isHaveTip = isHaveTip;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_school, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.itemSchoolTip, (position + 1) + "");
        holder.setText(R.id.textSchoolName, data.get(position).getName());
        holder.setText(R.id.itemSchoolPosition, data.get(position).getAreaNm());
        holder.setImage(context, R.id.itemSchoolImg, data.get(position).getImgUrl());


        TextView itemSchoolTip = holder.getView(R.id.itemSchoolTip);
        if (isHaveTip) {
            itemSchoolTip.setVisibility(View.VISIBLE);
        } else {
            itemSchoolTip.setVisibility(View.GONE);
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SchoolDetailActivity.class).putExtra("schoolId", data.get(position).getId()));
            }
        });

        XRecyclerView recyclerView = holder.getView(R.id.recyclerViewHotSchool);

        SchoolLabAdapter adapter = new SchoolLabAdapter(context, true);

        recyclerView.horizontalLayoutManager(context)
                .setAdapter(adapter);
        List<TipModel> list = new ArrayList<>();
        String[] educationTypes = data.get(position).getEducationTypeLabel().split(",");
        for (int i = 0; i < educationTypes.length; i++) {
            TipModel model = new TipModel();
            model.setValue(StringUtils.replace(educationTypes[i]));
            list.add(model);
        }
        adapter.setData(list);
    }
}
