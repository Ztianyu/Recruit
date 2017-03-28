package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.VocationalModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.VocationalListService;
import cn.zty.recruit.view.VocationalListView;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public class VocationalListPresenter extends IBasePresenter<VocationalListView> {

    VocationalListService service;

    public VocationalListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(VocationalListService.class);
    }

    private Observable<ResultBean<List<VocationalModel>>> submit(String name, String areaId,
                                                                 String discipline, String majorId,
                                                                 String examinationType, String score,
                                                                 int isHot, int pageNo, int pageSize) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        if (!TextUtils.isEmpty(name))
            params.put("name", name);
        if (!TextUtils.isEmpty(areaId))
            params.put("areaId", areaId);
        if (!TextUtils.isEmpty(discipline))
            params.put("discipline", discipline);
        if (!TextUtils.isEmpty(majorId))
            params.put("majorId", majorId);
        if (!TextUtils.isEmpty(examinationType))
            params.put("examinationType", examinationType);
        if (!TextUtils.isEmpty(score))
            params.put("score", score);
        if (isHot >= 0)
            params.put("isHot", isHot);
        params.put("pageSize", pageSize);
        params.put("pageNo", pageNo);
        return service.getVocationalSchoolList(params);
    }

    public void getVocationList(@Nullable String name, @Nullable String areaId,
                                @Nullable String discipline, @Nullable String majorId,
                                @Nullable String examinationType, @Nullable String score,
                                int isHot, int pageNo, int pageSize) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(name, areaId, discipline, majorId, examinationType, score, isHot, pageNo, pageSize), new RxSubscriber<List<VocationalModel>>() {
            @Override
            protected void _onNext(List<VocationalModel> models) {
                mView.onVocationalListSuccess(models);
            }
        }, false);
    }
}
