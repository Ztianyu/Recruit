package cn.zty.recruit.presenter;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.VocationService;
import cn.zty.recruit.view.VocationView;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public class VocationPresenter extends IBasePresenter<VocationView> {

    VocationService service;

    public VocationPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(VocationService.class);
    }

    private Observable<ResultBean<VocationalModel>> submit(String schoolId) {
        return service.getVocationalSchool(schoolId);
    }

    public void getVocationalSchool(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(schoolId), new RxSubscriber<VocationalModel>() {
            @Override
            protected void _onNext(VocationalModel model) {
                mView.onVocationSuccess(model);
            }
        }, false);
    }

}
