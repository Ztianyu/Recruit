package cn.zty.recruit.presenter;

import android.text.TextUtils;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.LoginModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.RegisterService;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.LoginView;
import rx.Observable;

/**
 * Created by zty on 2017/3/21.
 */

public class RegisterPresenter extends IBasePresenter<LoginView> {

    RegisterService service;

    public RegisterPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(RegisterService.class);
    }

    private Observable<ResultBean<LoginModel>> submit(String mobile, String password, String inviteCode) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("inviteCode", inviteCode);
        return service.register(params);
    }

    public void register(String mobile, String password, String surePassword, String inviteCode) {
        if (checkData(password, surePassword))
            mSubscription = RxManager.getInstance().doSubscribe1(submit(mobile, password, inviteCode), new RxSubscriber<LoginModel>() {
                @Override
                protected void _onNext(LoginModel loginModel) {
                    mView.onSuccess(loginModel);
                }
            });
    }

    private boolean checkData(String password, String surePassword) {
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入密码");
            return false;
        }
        if (TextUtils.isEmpty(surePassword)) {
            ToastUtils.show("请确认密码");
            return false;
        }
        if (!password.equals(surePassword)) {
            ToastUtils.show("两次密码不一致，请重新输入");
            return false;
        }
        return true;
    }
}
