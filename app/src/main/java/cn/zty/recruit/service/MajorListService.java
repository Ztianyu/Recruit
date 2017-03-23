package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.MajorModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface MajorListService {

    @GET(Urls.getSchoolMajorList)
    Observable<ResultBean<List<MajorModel>>> getSchoolMajorList(@Query("departmentId") String departmentId);
}
