package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.IntroductionModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.SchoolFacultyService;
import cn.zty.recruit.view.IntroductionView;

/**
 * 师资力量
 * Created by zty on 2017/3/22.
 */

public class SchoolFacultyPresenter extends IBasePresenter<IntroductionView> {

    SchoolFacultyService service;

    public SchoolFacultyPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(SchoolFacultyService.class);
    }

    public void getSchoolFaculty(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getSchoolFaculty(schoolId), new RxSubscriber<IntroductionModel>() {
            @Override
            protected void _onNext(IntroductionModel model) {
                mView.onIntroductionSuccess(model);
            }
        }, false);

    }

}
