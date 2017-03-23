package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.OrderModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface OrderListView extends IBaseView {
    void onOrderListSuccess(List<OrderModel> models);

    void onOrderDetail(OrderModel model);
}
