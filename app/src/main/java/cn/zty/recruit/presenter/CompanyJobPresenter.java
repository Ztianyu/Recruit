package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.CompanyJobModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.CompanyJobService;
import cn.zty.recruit.view.CompanyJobView;
import rx.Observable;

/**
 * Created by zty on 2017/5/31.
 */

public class CompanyJobPresenter extends IBasePresenter<CompanyJobView> {

    CompanyJobService service;

    public CompanyJobPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(CompanyJobService.class);
    }

    private Observable<ResultBean<List<CompanyJobModel>>> submit(int pageNo, int pageSize,
                                                                 String companyId, String companyNm,
                                                                 String name, String province,
                                                                 String city, String jobNature,
                                                                 String education, String industry) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        if (!TextUtils.isEmpty(companyId))
            params.put("companyId", companyId);
        if (!TextUtils.isEmpty(companyNm))
            params.put("companyNm", companyNm);
        if (!TextUtils.isEmpty(name))
            params.put("name", name);
        if (!TextUtils.isEmpty(province))
            params.put("province", province);
        if (!TextUtils.isEmpty(city))
            params.put("city", city);
        if (!TextUtils.isEmpty(jobNature))
            params.put("jobNature", jobNature);
        if (!TextUtils.isEmpty(education))
            params.put("education", education);
        if (!TextUtils.isEmpty(industry))
            params.put("industry", industry);

        return service.getCompanyJobList(params);
    }

    public void getCompanyJobList(int pageNo, int pageSize,
                                  @Nullable String companyId, @Nullable String companyNm,
                                  @Nullable String name, @Nullable String province,
                                  @Nullable String city, @Nullable String jobNature,
                                  @Nullable String education, @Nullable String industry) {
        mSubscription = RxManager.getInstance().doSubscribe1(
                submit(pageNo, pageSize, companyId, companyNm, name, province, city, jobNature, education, industry),
                new RxSubscriber<List<CompanyJobModel>>() {
                    @Override
                    protected void _onNext(List<CompanyJobModel> models) {
                        mView.onCompanyJob(models);
                    }
                }, false);
    }

}
