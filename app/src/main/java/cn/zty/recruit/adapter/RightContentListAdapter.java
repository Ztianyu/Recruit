package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.zty.linkage.adapter.RightMenuBaseListAdapter;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.RightContentEntity;
import cn.zty.recruit.holder.LeftListViewHolder;
import cn.zty.recruit.utils.ViewUtil;

/**
 * Created by zty on 2017/3/9.
 */

public class RightContentListAdapter extends RightMenuBaseListAdapter<LeftListViewHolder, RightContentEntity> {

    public RightContentListAdapter(Context ctx, List<RightContentEntity> list) {
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
        leftListViewHolder.itemText.setText("" + position + 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }
}
