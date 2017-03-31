package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.SubmitOrderService;
import cn.zty.recruit.view.StringView;
import rx.Observable;

/**
 * 提交订单
 * Created by zty on 2017/3/23.
 */

public class SubmitOrderPresenter extends IBasePresenter<StringView> {

    SubmitOrderService service;

    public SubmitOrderPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(SubmitOrderService.class);
    }

    private Observable<ResultBean<String>> submit(String office, String courseId,
                                                  String depositId, String fullNm,
                                                  String sex, String birthDate,
                                                  String mobile, String education,
                                                  String remarks) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        params.put("office", office);
        params.put("courseId", courseId);
        params.put("depositId", depositId);
        params.put("fullNm", fullNm);
        params.put("sex", sex);
        params.put("birthDate", birthDate);
        params.put("mobile", mobile);
        params.put("education", education);
        params.put("remarks", remarks);

        return service.submitOrder(params);
    }

    public void submitOrder(String office, String courseId,
                            String depositId, String fullNm,
                            String sex, String birthDate,
                            String mobile, String education,
                            @Nullable String remarks) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(office, courseId, depositId, fullNm, sex, birthDate, mobile, education, remarks), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(s);
            }
        }, true);
    }

}
