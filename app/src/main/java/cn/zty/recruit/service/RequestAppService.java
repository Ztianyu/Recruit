package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.VersionModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/4/6.
 */

public interface RequestAppService {
    @GET(Urls.requestApp)
    Observable<ResultBean<VersionModel>> requestApp(@Query("appName") String appName);
}
