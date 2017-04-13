package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.VisitService;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/4/13.
 */

public class VisitPresenter extends IBasePresenter<StringView> {
    VisitService service;

    public VisitPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(VisitService.class);
    }

    public void visit(String name, String phone, String schoolId) {

        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("fullNm", name);
        params.put("mobile", phone);
        params.put("schoolId", schoolId);

        mSubscription = RxManager.getInstance().doSubscribe1(service.visit(params), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(s);
            }
        }, false);

    }

}
