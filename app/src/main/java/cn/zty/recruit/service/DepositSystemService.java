package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.DepositSystemModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/23.
 */

public interface DepositSystemService {
    @GET(Urls.getDepositSystemList)
    Observable<ResultBean<List<DepositSystemModel>>> getDepositSystemList(@Query("schoolId") String schoolId);
}
