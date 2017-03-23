package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.NoticeModel;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface NoticeService {
    @GET(Urls.getNoticeList)
    Observable<ResultBean<List<NoticeModel>>> getNoticeList();
}
