package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.zty.baselib.adapter.MyBaseAdapter;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/3/15.
 */

public class DictAdapter extends MyBaseAdapter<TipModel> {

    private int type;

    public DictAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_text, null);
            holder.itemText = (TextView) convertView.findViewById(R.id.itemText);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (type == -1)
            holder.itemText.setTextColor(ResourceUtil.resToColor(context, R.color.gray));
        holder.itemText.setText(mData.get(position).getValue());
        return convertView;
    }

    static class Holder {
        TextView itemText;
    }
}
