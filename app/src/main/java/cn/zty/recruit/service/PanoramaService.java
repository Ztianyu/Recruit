package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.PanoramaModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface PanoramaService {
    @GET(Urls.getSchoolPanorama)
    Observable<ResultBean<List<PanoramaModel>>> getSchoolPanorama(@Query("schoolId") String schoolId);
}
