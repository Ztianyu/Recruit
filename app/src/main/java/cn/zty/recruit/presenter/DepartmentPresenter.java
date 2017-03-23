package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.CollegeModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.DepartmentListService;
import cn.zty.recruit.view.DepartmentListView;

/**
 * Created by zty on 2017/3/22.
 */

public class DepartmentPresenter extends IBasePresenter<DepartmentListView> {

    DepartmentListService service;

    public DepartmentPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(DepartmentListService.class);
    }

    public void getDepartmentList(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getDepartmentList(schoolId), new RxSubscriber<List<CollegeModel>>() {
            @Override
            protected void _onNext(List<CollegeModel> models) {
                mView.onDepartmentSuccess(models);
            }
        }, false);
    }
}
