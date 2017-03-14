package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.MajorModel;

/**
 * Created by zty on 2017/3/8.
 */

public class HotMajorAdapter extends MyBaseAdapter<MajorModel> {

    public HotMajorAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_major, null);
            holder.itemMajorTip = (TextView) convertView.findViewById(R.id.itemMajorTip);
            holder.itemMajorName = (TextView) convertView.findViewById(R.id.itemMajorName);
            holder.itemMajorCount = (TextView) convertView.findViewById(R.id.itemMajorCount);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.itemMajorTip.setText((position + 1) + "");
        holder.itemMajorName.setText((position + 1) + "：专业");
        return convertView;
    }

    static class Holder {
        TextView itemMajorTip;
        TextView itemMajorName;
        TextView itemMajorCount;
    }
}
