package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.UserModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/4/20.
 */

public interface InviteUserService {

    @GET(Urls.getInviteUserList)
    Observable<ResultBean<List<UserModel>>> getInviteUserList(@QueryMap RequestParams params);
}
