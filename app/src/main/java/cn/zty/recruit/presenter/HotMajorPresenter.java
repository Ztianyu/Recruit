package cn.zty.recruit.presenter;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
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
        return service.getHotMajorList(isHot, discipline, pageNo, pageSize);
    }

    public void getHotMajorList(int isHot, String discipline, int pageNo, int pageSize) {
        mSubscription = RxManager.getInstance().doSubscribe1(service.getHotMajorList(isHot, discipline, pageNo, pageSize), new RxSubscriber<List<MajorModel>>() {
            @Override
            protected void _onNext(List<MajorModel> majorModels) {
                mView.onHotMajorSuccess(majorModels);
            }
        }, false);
    }
}
