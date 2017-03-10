package cn.zty.linkage.model;

import android.view.View;

import java.util.List;

import cn.zty.linkage.adapter.LeftMenuBaseListAdapter;
import cn.zty.linkage.adapter.RightMenuBaseListAdapter;
import cn.zty.linkage.bean.BaseMenuBean;


/**
 * Created by zz on 2016/8/19.
 */
public interface ILinkage<T extends BaseMenuBean> {

    /**
     * Adapter for left ListView
     *
     * @param adapter #
     */
    void setLeftMenuAdapter(LeftMenuBaseListAdapter adapter);

    /**
     * Adapter for right ListView
     *
     * @param adapter #
     */
    void setRightContentAdapter(RightMenuBaseListAdapter adapter);

    /**
     * set data for left listView
     *
     * @param list #
     */
    void updateData(List<T> list);

    /**
     * set item click listenr for left and right ListView
     *
     * @param listener #
     */
    void setOnItemClickListener(OnItemClickListener listener);

    /**
     * the view showing when there's no data.
     *
     * @param view #
     */
    void setCustomNoDataView(View view);

    /**
     * the view layout id showing when there's no data.
     *
     * @param layoutId #
     */
    void setCustomNoDataViewWithLayoutId(int layoutId);

    interface OnItemClickListener {
        void onLeftClick(View itemView, int position);

        void onRightClick(View itemView, int position);
    }

}
