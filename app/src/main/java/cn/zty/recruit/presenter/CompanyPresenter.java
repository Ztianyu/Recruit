package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.CompanyModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.CompanyService;
import cn.zty.recruit.view.CompanyView;

/**
 * Created by zty on 2017/6/1.
 */

public class CompanyPresenter extends IBasePresenter<CompanyView> {

    CompanyService service;

    public CompanyPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(CompanyService.class);
    }

    public void getCompany(String companyId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getRecruitCompany(companyId), new RxSubscriber<CompanyModel>() {
            @Override
            protected void _onNext(CompanyModel companyModel) {
                mView.onCompany(companyModel);
            }
        }, false);

    }
}
