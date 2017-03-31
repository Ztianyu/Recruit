package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.StudySchoolModel;

/**
 * Created by zty on 2017/3/31.
 */

public interface StudySchoolView extends IBaseView {

    void onStudySchoolList(List<StudySchoolModel> models);

    void onStudySchool(StudySchoolModel model);
}
