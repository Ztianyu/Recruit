package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.AdsModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.AdsService;
import cn.zty.recruit.view.AdsView;

/**
 * Created by zty on 2017/3/22.
 */

public class GetAdsPresenter extends IBasePresenter<AdsView> {

    AdsService service;

    public GetAdsPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(AdsService.class);
    }

    public void getAds() {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getAds(), new RxSubscriber<List<AdsModel>>() {
            @Override
            protected void _onNext(List<AdsModel> models) {
                mView.onAdsSuccess(models);
            }
        }, false);
    }
}
