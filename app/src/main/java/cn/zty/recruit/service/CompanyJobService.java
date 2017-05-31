package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.CompanyJobModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/5/31.
 */

public interface CompanyJobService {
    @GET(Urls.getCompanyJobList)
    Observable<ResultBean<List<CompanyJobModel>>> getCompanyJobList(@QueryMap RequestParams params);
}
