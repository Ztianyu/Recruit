package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.VersionModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.RequestAppService;
import cn.zty.recruit.view.VersionView;

/**
 * Created by zty on 2017/4/6.
 */

public class VersionPresenter extends IBasePresenter<VersionView> {

    private static String appName = "app_android";

    RequestAppService service;

    public VersionPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(RequestAppService.class);
    }

    public void getVersion() {

        mSubscription = RxManager.getInstance().doSubscribe1(service.requestApp(appName), new RxSubscriber<VersionModel>() {
            @Override
            protected void _onNext(VersionModel versionModel) {
                mView.onSuccess(versionModel);
            }
        }, false);
    }
}
