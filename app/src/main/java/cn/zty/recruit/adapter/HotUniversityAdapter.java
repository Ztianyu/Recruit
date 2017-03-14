package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.baselib.widget.AutoLinefeedLayout;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.UniversityModel;
import cn.zty.recruit.ui.activity.school.SchoolDetailActivity;


/**
 * Created by zty on 2017/3/8.
 */

public class HotUniversityAdapter extends MyBaseAdapter<UniversityModel> {

    private boolean isHaveTip;

    public HotUniversityAdapter(Context context, boolean isHaveTip) {
        super(context);
        this.isHaveTip = isHaveTip;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_school, null);
            holder.itemSchoolTip = (TextView) convertView.findViewById(R.id.itemSchoolTip);
            holder.itemSchoolImg = (ImageView) convertView.findViewById(R.id.itemSchoolImg);
            holder.textSchoolName = (TextView) convertView.findViewById(R.id.textSchoolName);
            holder.autoLineLayout = (AutoLinefeedLayout) convertView.findViewById(R.id.autoLineLayout);
            holder.itemSchoolPosition = (TextView) convertView.findViewById(R.id.itemSchoolPosition);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.itemSchoolTip.setText((position + 1) + "");
        holder.textSchoolName.setText(position + "：学校");
        if (isHaveTip) {
            holder.itemSchoolTip.setVisibility(View.VISIBLE);
        } else {
            holder.itemSchoolTip.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SchoolDetailActivity.class));
            }
        });

        return convertView;
    }

    static class Holder {
        TextView itemSchoolTip;
        ImageView itemSchoolImg;
        TextView textSchoolName;
        AutoLinefeedLayout autoLineLayout;
        TextView itemSchoolPosition;
    }
}
