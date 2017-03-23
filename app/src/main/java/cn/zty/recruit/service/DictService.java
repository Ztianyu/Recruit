package cn.zty.recruit.service;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.recruit.base.Urls;
import cn.zty.recruit.bean.TipModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zty on 2017/3/22.
 */

public interface DictService {

    @GET(Urls.getDictList)
    Observable<ResultBean<List<TipModel>>> getDictList(@Query("type") String type);
    /**
     * 科学门类- school_discipline
     * 学校性质- school_nature
     * 教育类型- education_type
     * 考试类型- examination_type
     * 学历- education
     */
}
