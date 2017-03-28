package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zty.recruit.R;
import cn.zty.recruit.bean.DepartmentMajorModel;

/**
 * 专业介绍 列表
 * Created by zty on 2017/3/15.
 */

public class MajorListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<DepartmentMajorModel> mData;

    public MajorListAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return mData.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mData.get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CollegeAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new CollegeAdapter.ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_major_group, null);
            holder.name = (TextView) convertView.findViewById(R.id.textMajorExpandGroup);
            holder.indicator = (ImageView) convertView.findViewById(R.id.imgMajorExpandGroup);
            convertView.setTag(holder);
        } else {
            holder = (CollegeAdapter.ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(groupPosition).getMajorNm());
        if (isExpanded) {
            holder.indicator.setBackgroundResource(R.mipmap.ic_expand);
        } else {
            holder.indicator.setBackgroundResource(R.mipmap.ic_expand_normal);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        CollegeAdapter.ViewHolder2 holder = null;
        if (convertView == null) {
            holder = new CollegeAdapter.ViewHolder2();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_expand_content, null);
            holder.content = (TextView) convertView.findViewById(R.id.textExpandContent);
            convertView.setTag(holder);
        } else {
            holder = (CollegeAdapter.ViewHolder2) convertView.getTag();
        }

        holder.content.setText("　　" + mData.get(groupPosition).getRemarks());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setData(List<DepartmentMajorModel> models) {
        this.mData.clear();
        mData.addAll(models);
        notifyDataSetChanged();
    }
}
