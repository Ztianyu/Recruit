package cn.zty.recruit.service;


import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.LoginModel;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/3/4.
 */

public interface LoginService {

    @POST(Urls.login)
    Observable<ResultBean<LoginModel>> login(@QueryMap RequestParams params);

}
