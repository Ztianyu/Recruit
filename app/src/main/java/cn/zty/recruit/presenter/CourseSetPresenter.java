package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.CourseSetService;
import cn.zty.recruit.view.CourseSetView;

/**
 * 培训机构课程设置详情
 * Created by zty on 2017/3/23.
 */

public class CourseSetPresenter extends IBasePresenter<CourseSetView> {

    CourseSetService service;

    public CourseSetPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(CourseSetService.class);
    }

    public void getCourseSet(String courseId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getCourseSet(courseId), new RxSubscriber<InstitutionMajorModel>() {
            @Override
            protected void _onNext(InstitutionMajorModel majorModel) {
                mView.onCourseSetSuccess(majorModel);
            }
        }, false);
    }
}
