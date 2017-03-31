package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.StudyMajorModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/31.
 */

public interface MajorSettingService {
    @GET(Urls.getMajorSettingList)
    Observable<ResultBean<List<StudyMajorModel>>> getMajorList(@Query("departmentId") String departmentId);
}
