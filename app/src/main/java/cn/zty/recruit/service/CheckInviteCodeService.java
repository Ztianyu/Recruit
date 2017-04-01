package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/4/1.
 */

public interface CheckInviteCodeService {

    @GET(Urls.checkInviteCode)
    Observable<ResultBean<String>> check(@Query("inviteCode") String inviteCode);
}
