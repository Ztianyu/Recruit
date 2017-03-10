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
            holder.textMajor = (TextView) convertView.findViewById(R.id.textMajor);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textMajor.setText(position + "：专业");
        return convertView;
    }

    static class Holder {
        TextView textMajor;
    }
}
