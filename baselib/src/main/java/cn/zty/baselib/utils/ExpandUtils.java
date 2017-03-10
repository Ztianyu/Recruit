package cn.zty.baselib.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zty on 2017/3/8.
 */

public class ExpandUtils {

    public static void setExpand(Context context, RecyclerView recyclerView) {
        ExpandedLinearLayoutManager layoutManager = new ExpandedLinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);
//        防止滑动不流畅
        recyclerView.setNestedScrollingEnabled(false);
    }
}
