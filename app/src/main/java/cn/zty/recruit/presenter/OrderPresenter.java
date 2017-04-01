package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

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
import cn.zty.recruit.view.OrderView;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public class OrderPresenter extends IBasePresenter<OrderView> {

    OrderService service;

    public OrderPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(OrderService.class);
    }

    private Observable<ResultBean<List<OrderModel>>> submit(int state, int pageNo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        params.put("state", state);
        params.put("pageNo", pageNo);
        return service.getOrderList(params);
    }

    private Observable<ResultBean<OrderModel>> submit1(String orderId, String orderCode) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        if (!TextUtils.isEmpty(orderId))
            params.put("orderId", orderId);
        if (!TextUtils.isEmpty(orderCode))
            params.put("orderCode", orderCode);
        return service.getOrder(params);
    }

    public void getOrderList(int state, int pageNo) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(state, pageNo), new RxSubscriber<List<OrderModel>>() {
            @Override
            protected void _onNext(List<OrderModel> models) {
                mView.onOrderListSuccess(models);
            }
        }, false);
    }

    public void getOrder(@Nullable String orderId, @Nullable String orderCode) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit1(orderId, orderCode), new RxSubscriber<OrderModel>() {
            @Override
            protected void _onNext(OrderModel orderModel) {
                mView.onOrderDetail(orderModel);
            }
        }, false);
    }
}
