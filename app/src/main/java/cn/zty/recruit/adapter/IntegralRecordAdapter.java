package cn.zty.recruit.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.baselib.utils.TimeUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.IntegralRecordModel;

/**
 * Created by zty on 2017/3/21.
 */

public class IntegralRecordAdapter extends RecyclerAdapter<IntegralRecordModel, ViewHolder> {

    public IntegralRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_integral_record, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setText(R.id.textIntegralTime,
                TimeUtils.getChatTime(TimeUtils.dateToStamp(data.get(position).getCreateDate() + ":00")));

        holder.setText(R.id.textIntegralContent, MyTextUtils.notNullStr(data.get(position).getModeLabel()));

        holder.setText(R.id.textIntegralFrom, TextUtils.isEmpty(data.get(position).getSourceNm()) ? "" : "来源：" + data.get(position).getSourceNm());

        int mode = Integer.parseInt(data.get(position).getMode());

        holder.setText(R.id.textIntegralValue, (mode == 1 ? "-" : "+") +
                data.get(position).getIntegral());
        if (mode == 1) {
            ((TextView) holder.getView(R.id.textIntegralValue)).setTextColor(ResourceUtil.resToColor(context, R.color.red));
        } else {
            ((TextView) holder.getView(R.id.textIntegralValue)).setTextColor(ResourceUtil.resToColor(context, R.color.green));
        }
    }
}
