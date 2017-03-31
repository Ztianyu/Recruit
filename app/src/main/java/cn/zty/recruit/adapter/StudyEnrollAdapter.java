package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zty.recruit.R;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.bean.StudyMajorModel;
import cn.zty.recruit.ui.activity.learn.StudyEnrollActivity;

/**
 * Created by zty on 2017/3/18.
 */

public class StudyEnrollAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<CollegeModel> groupData;
    private Map<Integer, List<StudyMajorModel>> childData;

    public StudyEnrollAdapter(Context context) {
        this.mContext = context;
        this.groupData = new ArrayList<>();
        this.childData = new HashMap<>();
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_college_list, null);
            holder.name = (TextView) convertView.findViewById(R.id.textCollegeListName);
            holder.indicator = (ImageView) convertView.findViewById(R.id.imgCollegeExpand);
            convertView.setTag(holder);
        } else {
            holder = (CollegeAdapter.ViewHolder) convertView.getTag();
        }
        holder.name.setText(groupData.get(groupPosition).getName());
        if (isExpanded) {
            holder.indicator.setBackgroundResource(R.mipmap.ic_expand);
        } else {
            holder.indicator.setBackgroundResource(R.mipmap.ic_expand_normal);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ChildHolder holder = null;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_study_major_child, null);
            holder.textMajorChildName = (TextView) convertView.findViewById(R.id.textMajorChildName);
            holder.textStudyLength = (TextView) convertView.findViewById(R.id.textStudyLength);
            holder.textStudyEducation = (TextView) convertView.findViewById(R.id.textStudyEducation);
            holder.textStudyPrise = (TextView) convertView.findViewById(R.id.textStudyPrise);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        if (childData.get(groupPosition) != null) {
            holder.textMajorChildName.setText(childData.get(groupPosition).get(childPosition).getMajorNm());
            holder.textStudyLength.setText("学制" + childData.get(groupPosition).get(childPosition).getSchoolLength() + "年");
            holder.textStudyEducation.setText(childData.get(groupPosition).get(childPosition).getStudyTypeLabel());
            holder.textStudyPrise.setText(childData.get(groupPosition).get(childPosition).getRegistrationFee() +
                    "元/" +
                    childData.get(groupPosition).get(childPosition).getChargeStandardLabel()
                            .replace("按", "").replace("收", ""));
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, StudyEnrollActivity.class)
                        .putExtra("majorModel", childData.get(groupPosition).get(childPosition)));
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Map<Integer, List<StudyMajorModel>> getChildData() {
        return childData;
    }

    public void setData(List<CollegeModel> models) {
        this.groupData.clear();
        groupData.addAll(models);
        notifyDataSetChanged();
    }

    public List<CollegeModel> getData() {
        return this.groupData;
    }

    public void setChildData(List<StudyMajorModel> models, int groupPosition) {
        childData.put(groupPosition, models);
    }

    static class ChildHolder {
        TextView textMajorChildName;
        TextView textStudyLength;
        TextView textStudyEducation;
        TextView textStudyPrise;
    }
}
