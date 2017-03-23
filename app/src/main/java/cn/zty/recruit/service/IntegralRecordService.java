package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.IntegralRecordModel;
import cn.zty.recruit.bean.MajorModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface IntegralRecordService {
    @GET(Urls.getIntegralRecordList)
    Observable<ResultBean<List<IntegralRecordModel>>> getIntegralRecord(@QueryMap RequestParams params);
}
