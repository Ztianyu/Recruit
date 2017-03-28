package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.TrainingModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.GetTrainOrgListService;
import cn.zty.recruit.view.TrainOrgListView;
import rx.Observable;

/**
 * 培训机构
 * Created by zty on 2017/3/22.
 */

public class TrainOrgListPresenter extends IBasePresenter<TrainOrgListView> {
    GetTrainOrgListService service;

    public TrainOrgListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(GetTrainOrgListService.class);
    }

    private Observable<ResultBean<List<TrainingModel>>> submit(String name, String province, String city, String industryId, int pageNo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        if (!TextUtils.isEmpty(name))
            params.put("name", name);
        if (!TextUtils.isEmpty(province))
            params.put("province", province);
        if (!TextUtils.isEmpty(city))
            params.put("city", city);
        if (!TextUtils.isEmpty(industryId))
            params.put("industryId", industryId);
        params.put("pageNo", pageNo);
        return service.getTrainOrgList(params);
    }

    public void getTrainOrgList(@Nullable String name, @Nullable String province,
                                @Nullable String city, @Nullable String industryId, int pageNo) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(name, province, city, industryId, pageNo), new RxSubscriber<List<TrainingModel>>() {
            @Override
            protected void _onNext(List<TrainingModel> models) {
                mView.onTrainOrgListSuccess(models);
            }
        }, false);
    }
}
