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

public interface HotMajorService {
    @GET(Urls.getHotMajorList)
    Observable<ResultBean<List<MajorModel>>> getHotMajorList(@Query("isHot") int isHot,
                                                             @Query("discipline") String discipline,
                                                             @Query("pageNo") int pageNo,
                                                             @Query("pageSize") int pageSize);
}
