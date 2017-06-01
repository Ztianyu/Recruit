package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.CompanyModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/6/1.
 */

public interface CompanyService {
    @GET(Urls.getRecruitCompany)
    Observable<ResultBean<CompanyModel>> getRecruitCompany(@Query("companyId") String companyId);
}
