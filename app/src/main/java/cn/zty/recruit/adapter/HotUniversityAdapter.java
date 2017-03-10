package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.UniversityModel;


/**
 * Created by zty on 2017/3/8.
 */

public class HotUniversityAdapter extends MyBaseAdapter<UniversityModel> {

    public HotUniversityAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_university, null);
            holder.textUniversity = (TextView) convertView.findViewById(R.id.textUniversity);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textUniversity.setText(position + "：学校");
        return convertView;
    }

    static class Holder {
        TextView textUniversity;
    }
}
