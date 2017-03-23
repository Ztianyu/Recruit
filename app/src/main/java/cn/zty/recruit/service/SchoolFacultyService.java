package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.IntroductionModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface SchoolFacultyService {
    @GET(Urls.getSchoolFaculty)
    Observable<ResultBean<IntroductionModel>> getSchoolFaculty(@Query("schoolId") String schoolId);
}
