package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.IndustryTypeModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.IndustryTypeService;
import cn.zty.recruit.view.IndustryTypeView;

/**
 * Created by zty on 2017/3/22.
 */

public class IndustryTypePresenter extends IBasePresenter<IndustryTypeView> {
    IndustryTypeService service;

    public IndustryTypePresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(IndustryTypeService.class);
    }

    public void getIndustryTypeList() {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getIndustryTypeList(), new RxSubscriber<List<IndustryTypeModel>>() {
            @Override
            protected void _onNext(List<IndustryTypeModel> models) {
                mView.onIndustryTypeSuccess(models);
            }
        }, false);
    }
}
