package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.IntegralRecordModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.IntegralRecordService;
import cn.zty.recruit.view.IntegralRecordView;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public class IntegralRecordPresenter extends IBasePresenter<IntegralRecordView> {

    IntegralRecordService service;

    public IntegralRecordPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(IntegralRecordService.class);
    }

    private Observable<ResultBean<List<IntegralRecordModel>>> submit(int pageNo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        params.put("pageNo", pageNo);
        return service.getIntegralRecord(params);
    }

    public void getIntegralRecord(int pageNo) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(pageNo), new RxSubscriber<List<IntegralRecordModel>>() {
            @Override
            protected void _onNext(List<IntegralRecordModel> models) {
                mView.onIntegralRecordSuccess(models);
            }
        }, false);

    }
}
