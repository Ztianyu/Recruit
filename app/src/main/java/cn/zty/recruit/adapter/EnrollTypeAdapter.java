package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.listener.EnrollTypeSelectListener;

/**
 * Created by zty on 2017/3/17.
 */

public class EnrollTypeAdapter extends RecyclerAdapter<DepositSystemModel, ViewHolder> {

    private EnrollTypeSelectListener listener;

    public EnrollTypeAdapter(Context context, EnrollTypeSelectListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_text, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setText(R.id.itemText, (int) data.get(position).getAmount() + "(可抵" + (int) data.get(position).getDeductibleAmount() + "元)");

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEnrollTypeSelect(data.get(position));
            }
        });
    }
}
