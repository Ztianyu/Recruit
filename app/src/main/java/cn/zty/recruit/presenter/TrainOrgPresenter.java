package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.TrainOrgService;
import cn.zty.recruit.view.TrainOrgView;

/**
 * Created by zty on 2017/3/22.
 */

public class TrainOrgPresenter extends IBasePresenter<TrainOrgView> {

    TrainOrgService service;

    public TrainOrgPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(TrainOrgService.class);
    }

    public void getTrainOrg(String orgId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getTrainOrg(orgId), new RxSubscriber<TrainingModel>() {
            @Override
            protected void _onNext(TrainingModel trainingModel) {
                mView.onTrainOrgSuccess(trainingModel);
            }
        }, false);
    }

}
