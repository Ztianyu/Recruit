package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.PlanModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public interface ScheduleSetListService {
    @GET(Urls.getScheduleSetList)
    Observable<ResultBean<List<PlanModel>>> getScheduleSetList(@Query("courseId") String courseId);
}
