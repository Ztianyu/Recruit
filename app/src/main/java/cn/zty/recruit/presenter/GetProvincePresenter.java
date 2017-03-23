package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.GetProvinceService;
import cn.zty.recruit.view.AreaView;

/**
 * Created by zty on 2017/3/22.
 */

public class GetProvincePresenter extends IBasePresenter<AreaView> {

    GetProvinceService service;

    public GetProvincePresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(GetProvinceService.class);
    }

    public void getProvince() {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getProvinceArea(), new RxSubscriber<List<TipModel>>() {
            @Override
            protected void _onNext(List<TipModel> models) {
                mView.onAreaSuccess(0, models);
            }
        }, false);
    }
}
