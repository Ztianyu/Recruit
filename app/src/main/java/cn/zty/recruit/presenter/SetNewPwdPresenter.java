package cn.zty.recruit.presenter;

import android.text.TextUtils;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.SetNewPwdService;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;
import rx.Observable;

/**
 * Created by zty on 2017/3/21.
 */

public class SetNewPwdPresenter extends IBasePresenter<StringView> {

    SetNewPwdService service;

    public SetNewPwdPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(SetNewPwdService.class);
    }

    private Observable<ResultBean<String>> submit(String newPassword) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("loginName", RecruitApplication.getInstance().getLoginName());
        params.put("newPassword", newPassword);
        return service.setNewPwd(params);
    }

    public void setNewPwd(String newPassword, String surePassword) {
        if (checkData(newPassword, surePassword))
            mSubscription = RxManager.getInstance().doSubscribe1(submit(newPassword), new RxSubscriber<String>() {
                @Override
                protected void _onNext(String msg) {
                    mView.onSuccess(msg);
                }
            },false);
    }

    private boolean checkData(String password, String surePassword) {
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入新密码");
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
