package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.StudySchoolModel;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zty on 2017/3/31.
 */

public interface StudySchoolService {

    @GET(Urls.getStudySchoolInfoList)
    Observable<ResultBean<List<StudySchoolModel>>> getStudySchoolInfoList(@QueryMap RequestParams params);
}
