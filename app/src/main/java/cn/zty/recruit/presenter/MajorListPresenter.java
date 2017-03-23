package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.MajorListService;
import cn.zty.recruit.view.MajorListView;

/**
 * Created by zty on 2017/3/22.
 */

public class MajorListPresenter extends IBasePresenter<MajorListView> {

    MajorListService service;

    public MajorListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(MajorListService.class);
    }

    public void getSchoolMajorList(String departmentId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getSchoolMajorList(departmentId), new RxSubscriber<List<MajorModel>>() {
            @Override
            protected void _onNext(List<MajorModel> majorModels) {
                mView.onMajorListSuccess(majorModels);
            }
        }, false);
    }
}
