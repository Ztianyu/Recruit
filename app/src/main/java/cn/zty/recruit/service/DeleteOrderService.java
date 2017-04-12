package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/4/12.
 */

public interface DeleteOrderService {
    @POST(Urls.deleteOrder)
    Observable<ResultBean<String>> deleteOrder(@QueryMap RequestParams params);
}
