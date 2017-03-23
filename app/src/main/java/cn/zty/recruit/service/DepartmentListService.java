package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.CollegeModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface DepartmentListService {
    @GET(Urls.getSchoolDepartmentList)
    Observable<ResultBean<List<CollegeModel>>> getDepartmentList(@Query("schoolId") String schoolId);
}
