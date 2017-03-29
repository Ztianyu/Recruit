package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.InstitutionMajorModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public interface CourseSetListService {
    @GET(Urls.getCourseSetList)
    Observable<ResultBean<List<InstitutionMajorModel>>> getCourseSetList(@Query("schoolId") String schoolId,
                                                                         @Query("pageNo") int pageNo);
}
