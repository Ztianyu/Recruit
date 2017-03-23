package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.InstitutionMajorModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface CourseSetView extends IBaseView {
    void onCourseSetSuccess(InstitutionMajorModel majorModel);
}
