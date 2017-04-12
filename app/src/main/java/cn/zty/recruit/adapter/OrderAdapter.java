package cn.zty.recruit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.listener.DeleteOrderListener;
import cn.zty.recruit.ui.activity.person.OrderDetailActivity;
import cn.zty.recruit.utils.DialogUtils;

import static cn.zty.recruit.base.Constants.chargeStandard1;
import static cn.zty.recruit.base.Constants.chargeStandard2;

/**
 * Created by zty on 2017/3/30.
 */

public class OrderAdapter extends RecyclerAdapter<OrderModel, ViewHolder> {

    private FragmentManager manager;
    private DeleteOrderListener listener;

    public OrderAdapter(Context context, FragmentManager manager, DeleteOrderListener listener) {
        super(context);
        this.manager = manager;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_order, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RelativeLayout stubOrderTop = holder.getView(R.id.stubOrderTop);
        RelativeLayout stubOrderBottom = holder.getView(R.id.stubOrderBottom);
        LinearLayout layoutDeposit = holder.getView(R.id.layoutDeposit);

        holder.setImage(context, R.id.imgOrder, data.get(position).getImgUrl());
        holder.setText(R.id.textOrderSchoolNm, data.get(position).getSchoolNm());
        holder.setText(R.id.textOrderCourseNm, data.get(position).getCourseNm());
        if (data.get(position).getChargeStandard().equals("1")) {
            holder.setText(R.id.textOrderPrise, data.get(position).getMoney() + "元/" + chargeStandard1);
        } else {
            holder.setText(R.id.textOrderPrise, data.get(position).getMoney() + " 元/" + chargeStandard2);
        }
        holder.setText(R.id.textOrderDeposit, data.get(position).getDeposit() + "");
        final int state = Integer.parseInt(data.get(position).getState());

        if (state == 0) {
//            stubOrderTop.setVisibility(View.GONE);
            stubOrderBottom.setVisibility(View.GONE);
            holder.setText(R.id.textOrderTop, "订单编号：" + data.get(position).getOrderCode());
        } else if (state == 1) {
            stubOrderTop.setVisibility(View.VISIBLE);
            stubOrderBottom.setVisibility(View.GONE);
            holder.setText(R.id.textOrderTop, "待支付尾款：" + data.get(position).getActualPayment());
            ((TextView) holder.getView(R.id.textOrderTop)).setTextColor(ResourceUtil.resToColor(context, R.color.colorAccent));
        } else if (state == 2) {
            layoutDeposit.setVisibility(View.VISIBLE);
            holder.setText(R.id.textOrderTop, "订单编号：" + data.get(position).getOrderCode());
            holder.setText(R.id.textOrderBottom, (data.get(position).getDeposit() + data.get(position).getActualPayment()) + "");
        }

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, OrderDetailActivity.class)
                        .putExtra("orderCode", data.get(position).getOrderCode()));
            }
        });

        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (state == 0) {
                    DialogUtils.showDelete(manager, position, listener);
                    return true;
                }
                return false;
            }
        });
    }

    public List<OrderModel> getData() {
        return data;
    }
}
