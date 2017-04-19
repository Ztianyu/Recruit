package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.DepositSystemService;
import cn.zty.recruit.view.DepositSystemView;

/**
 * 获取定金类型
 * Created by zty on 2017/3/23.
 */

public class DepositSystemPresenter extends IBasePresenter<DepositSystemView> {

    DepositSystemService service;

    public DepositSystemPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(DepositSystemService.class);
    }

    public void getDepositSystemList(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getDepositSystemList(schoolId), new RxSubscriber<List<DepositSystemModel>>() {
            @Override
            protected void _onNext(List<DepositSystemModel> models) {
                mView.onDepositSystemSuccess(models);
            }
        }, false);
    }
}
