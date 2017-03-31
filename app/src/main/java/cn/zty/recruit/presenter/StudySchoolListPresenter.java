package cn.zty.recruit.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import cn.zty.baselib.bean.ResultBean;
import cn.zty.baselib.http.RequestParams;
import cn.zty.baselib.http.RetrofitHelper;
import cn.zty.baselib.presenter.IBasePresenter;
import cn.zty.recruit.base.RequestParamsHelper;
import cn.zty.recruit.bean.StudySchoolModel;
import cn.zty.recruit.rx.RxManager;
import cn.zty.recruit.rx.RxSubscriber;
import cn.zty.recruit.service.StudySchoolService;
import cn.zty.recruit.view.StudySchoolView;
import rx.Observable;

/**
 * Created by zty on 2017/3/31.
 */

public class StudySchoolListPresenter extends IBasePresenter<StudySchoolView> {

    StudySchoolService service;

    public StudySchoolListPresenter() {
        service = RetrofitHelper.getInstance().getRetrofit().create(StudySchoolService.class);
    }

    private Observable<ResultBean<List<StudySchoolModel>>> submit(String name, String province,
                                                                  String city, String discipline,
                                                                  String majorId, String schoolType,
                                                                  String studyType, String tuitionRange,
                                                                  int pageNo) {
        RequestParams params = RequestParamsHelper.getInstance().getRequestParams();
        if (!TextUtils.isEmpty(name))
            params.put("name", name);
        if (!TextUtils.isEmpty(province))
            params.put("province", province);
        if (!TextUtils.isEmpty(city))
            params.put("city", city);
        if (!TextUtils.isEmpty(discipline))
            params.put("discipline", discipline);
        if (!TextUtils.isEmpty(majorId))
            params.put("majorId", majorId);
        if (!TextUtils.isEmpty(schoolType))
            params.put("schoolType", schoolType);
        if (!TextUtils.isEmpty(studyType))
            params.put("studyType", studyType);
        if (!TextUtils.isEmpty(tuitionRange))
            params.put("tuitionRange", tuitionRange);

        params.put("pageNo", pageNo);
        return service.getStudySchoolInfoList(params);
    }

    public void getStudySchoolInfoList(@Nullable String name, @Nullable String province,
                                       @Nullable String city, @Nullable String discipline,
                                       @Nullable String majorId, @Nullable String schoolType,
                                       @Nullable String studyType, @Nullable String tuitionRange,
                                       int pageNo) {
        mSubscription = RxManager.getInstance().doSubscribe1(submit(name, province, city, discipline, majorId, schoolType, studyType, tuitionRange, pageNo),
                new RxSubscriber<List<StudySchoolModel>>() {
                    @Override
                    protected void _onNext(List<StudySchoolModel> models) {
                        mView.onStudySchoolList(models);
                    }
                }, false);
    }
}
