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
import cn.zty.recruit.service.UpdatePwdService;
import cn.zty.recruit.utils.ToastUtils;
import cn.zty.recruit.view.StringView;
import rx.Observable;

/**
 * Created by zty on 2017/3/21.
 */

public class UpdatePwdPresenter extends IBasePresenter<StringView> {

    UpdatePwdService service;

    public UpdatePwdPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(UpdatePwdService.class);
    }

    private Observable<ResultBean<String>> submit(String oldPassword, String newPassword) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("loginName", RecruitApplication.getInstance().getLoginName());
        params.put("oldPassword", oldPassword);
        params.put("newPassword", newPassword);
        return service.updatePwd(params);
    }

    public void updatePwd(String oldPassword, String newPassword, String surePassword) {
        if (checkData(oldPassword, newPassword, surePassword))
            mSubscription = RxManager.getInstance().doSubscribe1(submit(oldPassword, newPassword), new RxSubscriber<String>() {
                @Override
                protected void _onNext(String msg) {
                    mView.onSuccess(msg);
                }
            },true);
    }

    private boolean checkData(String oldPassword, String password, String surePassword) {
        if (TextUtils.isEmpty(oldPassword)) {
            ToastUtils.show("请输入原密码");
            return false;
        }
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
