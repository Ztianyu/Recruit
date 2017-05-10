package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.CheckInviteCodeService;
import cn.zty.recruit.view.StringView;

/**
 * Created by zty on 2017/4/1.
 */

public class CheckInviteCodePresenter extends IBasePresenter<StringView> {

    CheckInviteCodeService service;

    public CheckInviteCodePresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(CheckInviteCodeService.class);
    }

    public void check(String inviteCode) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.check(inviteCode.replace(" ", "")), new RxSubscriber<String>() {
            @Override
            protected void _onNext(String s) {
                mView.onSuccess(s);
            }
        }, true);

    }
}
