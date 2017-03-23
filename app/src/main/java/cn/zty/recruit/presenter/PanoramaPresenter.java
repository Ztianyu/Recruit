package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.PanoramaModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.PanoramaService;
import cn.zty.recruit.view.PanoramaView;

/**
 * Created by zty on 2017/3/22.
 */

public class PanoramaPresenter extends IBasePresenter<PanoramaView> {
    PanoramaService service;

    public PanoramaPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(PanoramaService.class);
    }

    public void getSchoolPanorama(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getSchoolPanorama(schoolId), new RxSubscriber<PanoramaModel>() {
            @Override
            protected void _onNext(PanoramaModel model) {
                mView.onPanoramaSuccess(model);
            }
        }, false);
    }
}
