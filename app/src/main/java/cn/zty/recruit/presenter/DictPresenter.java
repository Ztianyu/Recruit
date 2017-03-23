package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.DictService;
import cn.zty.recruit.view.DictListView;

/**
 * Created by zty on 2017/3/22.
 */

public class DictPresenter extends IBasePresenter<DictListView> {

    DictService service;

    public DictPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(DictService.class);
    }

    public void getDictList(final String type) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getDictList(type), new RxSubscriber<List<TipModel>>() {
            @Override
            protected void _onNext(List<TipModel> models) {
                mView.onDictSuccess(type, models);
            }
        }, false);
    }
}
