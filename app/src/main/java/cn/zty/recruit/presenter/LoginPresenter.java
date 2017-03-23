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
import cn.zty.recruit.service.LoginService;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.LoginView;
import rx.Observable;

/**
 * Created by zty on 2017/3/4.
 */

public class LoginPresenter extends IBasePresenter<LoginView> {

    LoginService service;

    public LoginPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(LoginService.class);
    }

    private Observable<ResultBean<LoginModel>> submit(String loginName, String passWord) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("loginName", loginName);
        params.put("passWord", passWord);
        return service.login(params);
    }

    public void login(String loginName, String passWord) {
        if (checkData(loginName, passWord))
            mSubscription = RxManager.getInstance().doSubscribe1(submit(loginName, passWord), new RxSubscriber<LoginModel>() {
                @Override
                protected void _onNext(LoginModel loginModel) {
                    mView.onLoginSuccess(loginModel);
                }
            },true);
    }

    private boolean checkData(String loginName, String passWord) {
        if (TextUtils.isEmpty(loginName)) {
            ToastUtils.show("请输入登录名");
            return false;
        }
        if (TextUtils.isEmpty(passWord)) {
            ToastUtils.show("请输入密码");
            return false;
        }
        return true;
    }
}
