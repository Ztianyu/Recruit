package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.HotMajorService;
import cn.zty.recruit.view.HotMajorView;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public class HotMajorPresenter extends IBasePresenter<HotMajorView> {

    HotMajorService service;

    public HotMajorPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(HotMajorService.class);
    }

    private Observable<ResultBean<List<MajorModel>>> submit(int isHot, String discipline, int pageNo, int pageSize) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        if (isHot >= 0)
            params.put("isHot", isHot);
        if (!TextUtils.isEmpty(discipline))
            params.put("discipline", discipline);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return service.getHotMajorList(params);
    }

    public void getHotMajorList(int isHot, @Nullable String discipline, int pageNo, int pageSize) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(isHot, discipline, pageNo, pageSize), new RxSubscriber<List<MajorModel>>() {
            @Override
            protected void _onNext(List<MajorModel> majorModels) {
                mView.onHotMajorSuccess(majorModels);
            }
        }, false);
    }
}
