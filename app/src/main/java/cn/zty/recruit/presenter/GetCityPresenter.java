package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.GetCityService;
import cn.zty.recruit.view.AreaView;

/**
 * Created by zty on 2017/3/22.
 */

public class GetCityPresenter extends IBasePresenter<AreaView> {
    GetCityService service;

    public GetCityPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(GetCityService.class);
    }

    public void getCity(String provinceId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getCityArea(provinceId), new RxSubscriber<List<TipModel>>() {
            @Override
            protected void _onNext(List<TipModel> models) {
                mView.onAreaSuccess(1, models);
            }
        }, false);

    }
}
