package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.InstitutionMajorModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.CourseSetListService;
import cn.zty.recruit.view.CourseSetListView;

/**
 * 培训机构课程设置列表
 * Created by zty on 2017/3/23.
 */

public class CourseSetListPresenter extends IBasePresenter<CourseSetListView> {

    CourseSetListService service;

    public CourseSetListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(CourseSetListService.class);
    }

    public void getCourseSetList(String schoolId, int pageNo) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getCourseSetList(schoolId, pageNo), new RxSubscriber<List<InstitutionMajorModel>>() {
            @Override
            protected void _onNext(List<InstitutionMajorModel> majorModels) {
                mView.onCourseSetSuccess(majorModels);
            }
        }, false);
    }
}
