package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.PlanModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.ScheduleSetListService;
import cn.zty.recruit.view.ScheduleSetListView;

/**
 * 培训机构课程课目列表
 * Created by zty on 2017/3/23.
 */

public class ScheduleSetListPresenter extends IBasePresenter<ScheduleSetListView> {

    ScheduleSetListService service;

    public ScheduleSetListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(ScheduleSetListService.class);
    }

    public void getScheduleSetList(String courseId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getScheduleSetList(courseId), new RxSubscriber<List<PlanModel>>() {
            @Override
            protected void _onNext(List<PlanModel> models) {
                mView.onScheduleSetSuccess(models);
            }
        }, false);
    }

}
