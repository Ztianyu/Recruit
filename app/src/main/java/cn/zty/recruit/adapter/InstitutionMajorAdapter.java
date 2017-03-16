package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.InstitutionMajorModel;

/**
 * Created by zty on 2017/3/16.
 */

public class InstitutionMajorAdapter extends MyBaseAdapter<InstitutionMajorModel> {

    public InstitutionMajorAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_institution_major, null);
            holder.textInstitutionMajorTip = (TextView) convertView.findViewById(R.id.textInstitutionMajorTip);
            holder.textMajorName = (TextView) convertView.findViewById(R.id.textMajorName);
            holder.textMajorIntroduction = (TextView) convertView.findViewById(R.id.textMajorIntroduction);
            holder.textMajorPrise = (TextView) convertView.findViewById(R.id.textMajorPrise);
            holder.textMajorUnit = (TextView) convertView.findViewById(R.id.textMajorUnit);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        return convertView;
    }

    static class Holder {
        TextView textInstitutionMajorTip;
        TextView textMajorName;
        TextView textMajorIntroduction;
        TextView textMajorPrise;
        TextView textMajorUnit;
    }
}
