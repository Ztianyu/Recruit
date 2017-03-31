package cn.zty.recruit.service;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.StudySchoolModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/31.
 */

public interface StudySchoolInfoService {

    @GET(Urls.getStudySchoolInfo)
    Observable<ResultBean<StudySchoolModel>> getStudySchoolInfo(@Query("schoolId") String schoolId);
}
