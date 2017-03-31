package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.StudyMajorModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.MajorSettingService;
import cn.zty.recruit.view.StudyMajorView;

/**
 * Created by zty on 2017/3/31.
 */

public class MajorSettingPresenter extends IBasePresenter<StudyMajorView> {

    MajorSettingService service;

    public MajorSettingPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(MajorSettingService.class);
    }

    public void getMajorList(String departmentId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getMajorList(departmentId), new RxSubscriber<List<StudyMajorModel>>() {
            @Override
            protected void _onNext(List<StudyMajorModel> majorModels) {
                mView.onStudyMajorList(majorModels);
            }
        }, false);
    }

}
