package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.UserModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/3/21.
 */

public interface GetUserService {
    @GET(Urls.getUserDataDetail)
    Observable<ResultBean<UserModel>> getUserDataDetail(@QueryMap RequestParams params);
}
