package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.StudySchoolModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.StudySchoolInfoService;
import cn.zty.recruit.view.StudySchoolView;

/**
 * Created by zty on 2017/3/31.
 */

public class StudySchoolInfoPresenter extends IBasePresenter<StudySchoolView> {

    StudySchoolInfoService service;

    public StudySchoolInfoPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(StudySchoolInfoService.class);
    }

    public void getStudySchoolInfo(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getStudySchoolInfo(schoolId), new RxSubscriber<StudySchoolModel>() {
            @Override
            protected void _onNext(StudySchoolModel studySchoolModel) {
                mView.onStudySchool(studySchoolModel);
            }
        }, false);
    }
}
