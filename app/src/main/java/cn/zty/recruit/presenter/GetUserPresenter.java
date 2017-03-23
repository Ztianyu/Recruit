package cn.zty.recruit.presenter;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.GetUserService;
import cn.zty.recruit.view.UserView;
import rx.Observable;

/**
 * Created by zty on 2017/3/21.
 */

public class GetUserPresenter extends IBasePresenter<UserView> {
    GetUserService service;

    public GetUserPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(GetUserService.class);
    }

    private Observable<ResultBean<UserModel>> submit(String tokenId, String userId) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", tokenId);
        params.put("userId", userId);
        return service.getUserDataDetail(params);
    }

    public void getUser(String tokenId, String userId) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(tokenId, userId), new RxSubscriber<UserModel>() {
            @Override
            protected void _onNext(UserModel userModel) {
                mView.onUserSuccess(userModel);
            }
        },false);
    }
}
