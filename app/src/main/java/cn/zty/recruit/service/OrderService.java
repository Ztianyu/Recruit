package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.OrderModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public interface OrderService {
    @GET(Urls.getOrderList)
    Observable<ResultBean<List<OrderModel>>> getOrderList(@QueryMap RequestParams params);

    @GET(Urls.getOrder)
    Observable<ResultBean<OrderModel>> getOrder(@QueryMap RequestParams params);
}
