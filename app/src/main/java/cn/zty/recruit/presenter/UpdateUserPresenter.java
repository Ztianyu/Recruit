package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.UserModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.UpdateUserService;
import cn.zty.recruit.view.UserView;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public class UpdateUserPresenter extends IBasePresenter<UserView> {

    UpdateUserService service;

    public UpdateUserPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(UpdateUserService.class);
    }

    private Observable<ResultBean<UserModel>> submit(String nickName,String fullNm, String sex,
                                                     String mobile, String birthDate,
                                                     String education, String province,
                                                     String city, String photo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("tokenId", RecruitApplication.getInstance().getTokenId());
        params.put("userId", RecruitApplication.getInstance().getUserId());
        if (RecruitApplication.getInstance().getUserModel() != null)
            params.put("id", RecruitApplication.getInstance().getUserModel().getId());
        if (!TextUtils.isEmpty(nickName))
            params.put("fullNm", nickName);
        if (!TextUtils.isEmpty(fullNm))
            params.put("fullNm", fullNm);
        if (!TextUtils.isEmpty(sex))
            params.put("sex", sex);
        if (!TextUtils.isEmpty(mobile))
            params.put("mobile", mobile);
        if (!TextUtils.isEmpty(birthDate))
            params.put("birthDate", birthDate);
        if (!TextUtils.isEmpty(education))
            params.put("education", education);
        if (!TextUtils.isEmpty(province))
            params.put("province", province);
        if (!TextUtils.isEmpty(city))
            params.put("city", city);
        if (!TextUtils.isEmpty(photo))
            params.put("photo", photo);

        return service.update(params);
    }

    public void update(@Nullable String nickName, @Nullable String fullNm, @Nullable String sex,
                       @Nullable String mobile, @Nullable String birthDate,
                       @Nullable String education, @Nullable String province,
                       @Nullable String city, @Nullable String photo) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(nickName,fullNm, sex, mobile, birthDate, education, province, city, photo), new RxSubscriber<UserModel>() {
            @Override
            protected void _onNext(UserModel userModel) {
                mView.onUserSuccess(userModel);
            }
        }, false);
    }

}
