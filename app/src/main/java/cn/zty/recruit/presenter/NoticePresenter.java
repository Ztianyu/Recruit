package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.NoticeModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.NoticeService;
import cn.zty.recruit.view.NoticeView;

/**
 * Created by zty on 2017/3/22.
 */

public class NoticePresenter extends IBasePresenter<NoticeView> {

    NoticeService service;

    public NoticePresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(NoticeService.class);
    }

    public void getNotice() {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getNoticeList(), new RxSubscriber<List<NoticeModel>>() {
            @Override
            protected void _onNext(List<NoticeModel> models) {
                mView.onNoticeSuccess(models);
            }
        }, false);
    }
}
