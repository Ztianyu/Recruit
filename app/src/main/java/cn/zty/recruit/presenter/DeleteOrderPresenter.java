package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.DeleteOrderService;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/4/12.
 */

public class DeleteOrderPresenter extends IBasePresenter<StringView> {

    DeleteOrderService service;

    public DeleteOrderPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(DeleteOrderService.class);
    }

    public void delete(String orderId) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("orderId", orderId);

        mSubscription = RxManager.getInstance().doSubscribe1(service.deleteOrder(params), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(s);
            }
        }, false);
    }
}
