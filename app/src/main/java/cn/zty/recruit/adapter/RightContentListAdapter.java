package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.zty.linkage.adapter.RightMenuBaseListAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.holder.LeftListViewHolder;

/**
 * Created by zty on 2017/3/9.
 */

public class RightContentListAdapter extends RightMenuBaseListAdapter<LeftListViewHolder, TipModel> {

    public RightContentListAdapter(Context ctx, List<TipModel> list) {
        super(ctx, list);
    }

    @Override
    public LeftListViewHolder getViewHolder() {
        return new LeftListViewHolder();
    }

    @Override
    public void bindView(LeftListViewHolder leftListViewHolder, View itemView) {
//        ViewUtil.scaleContentView((ViewGroup) itemView.findViewById(R.id.layoutText));
        leftListViewHolder.itemText = (TextView) itemView.findViewById(R.id.itemText);
    }

    @Override
    public void bindData(LeftListViewHolder leftListViewHolder, int position) {
        leftListViewHolder.itemText.setText(list.get(position).getValue());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }
}
