package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.IndustryTypeModel;

/**
 * Created by zty on 2017/3/28.
 */

public class IndustryTypeAdapter extends MyBaseAdapter<IndustryTypeModel> {

    public IndustryTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DictAdapter.Holder holder = null;
        if (convertView == null) {
            holder = new DictAdapter.Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text, null);
            holder.itemText = (TextView) convertView.findViewById(R.id.itemText);
            convertView.setTag(holder);
        } else {
            holder = (DictAdapter.Holder) convertView.getTag();
        }
        holder.itemText.setTextColor(ResourceUtil.resToColor(context, R.color.gray));
        holder.itemText.setText(mData.get(position).getName());
        return convertView;
    }
}
