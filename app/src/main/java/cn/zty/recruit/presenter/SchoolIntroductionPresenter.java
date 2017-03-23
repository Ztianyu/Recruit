package cn.zty.recruit.presenter;

import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.bean.IntroductionModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.SchoolIntroductionService;
import cn.zty.recruit.view.IntroductionView;

/**
 * Created by zty on 2017/3/22.
 */

public class SchoolIntroductionPresenter extends IBasePresenter<IntroductionView> {

    SchoolIntroductionService service;

    public SchoolIntroductionPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(SchoolIntroductionService.class);
    }

    public void getSchoolIntroduction(String schoolId) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getSchoolIntroduction(schoolId), new RxSubscriber<IntroductionModel>() {
            @Override
            protected void _onNext(IntroductionModel model) {
                mView.onIntroductionSuccess(model);
            }
        }, false);
    }

}
