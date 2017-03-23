package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.InstitutionMajorModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface CourseSetListView extends IBaseView {
    void onCourseSetSuccess(List<InstitutionMajorModel> majorModels);
}
