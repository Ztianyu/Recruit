package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.OrderModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.OrderService;
import cn.zty.recruit.view.OrderListView;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public class OrderListPresenter extends IBasePresenter<OrderListView> {

    OrderService service;

    public OrderListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(OrderService.class);
    }

    private Observable<ResultBean<List<OrderModel>>> submit(int state) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        params.put("state", state);
        return service.getOrderList(params);
    }

    private Observable<ResultBean<OrderModel>> submit1(String orderId) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        params.put("state", orderId);
        return service.getOrder(params);
    }

    public void getOrderList(int state) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(state), new RxSubscriber<List<OrderModel>>() {
            @Override
            protected void _onNext(List<OrderModel> models) {
                mView.onOrderListSuccess(models);
            }
        }, false);
    }

    public void getOrderList(String orderId) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit1(orderId), new RxSubscriber<OrderModel>() {
            @Override
            protected void _onNext(OrderModel orderModel) {
                mView.onOrderDetail(orderModel);
            }
        }, false);
    }
}
