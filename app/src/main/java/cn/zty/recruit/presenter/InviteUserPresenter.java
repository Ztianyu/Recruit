package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.InviteUserService;
import cn.zty.recruit.view.InviteUserView;

/**
 * Created by zty on 2017/4/20.
 */

public class InviteUserPresenter extends IBasePresenter<InviteUserView> {

    InviteUserService service;

    public InviteUserPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(InviteUserService.class);
    }

    public void getInviteUserList(int pageNo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("pageNo", pageNo);
        mSubscription = RxManager.getInstance().doSubscribe1(service.getInviteUserList(params), new RxSubscriber<List<UserModel>>() {
            @Override
            protected void _onNext(List<UserModel> models) {
                mView.onInviteUser(models);
            }
        }, false);

    }
}
