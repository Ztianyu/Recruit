package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.zty.linkage.adapter.RightMenuBaseListAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.holder.LeftListViewHolder;

/**
 * Created by zty on 2017/3/23.
 */

public class RightMajorListAdapter extends RightMenuBaseListAdapter<LeftListViewHolder, MajorModel> {

    public RightMajorListAdapter(Context ctx, List<MajorModel> list) {
        super(ctx, list);
    }

    @Override
    public LeftListViewHolder getViewHolder() {
        return new LeftListViewHolder();
    }

    @Override
    public void bindView(LeftListViewHolder leftListViewHolder, View itemView) {
        leftListViewHolder.itemText = (TextView) itemView.findViewById(R.id.itemText);
    }

    @Override
    public void bindData(LeftListViewHolder leftListViewHolder, int position) {
        leftListViewHolder.itemText.setText("" + position + 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }
}
