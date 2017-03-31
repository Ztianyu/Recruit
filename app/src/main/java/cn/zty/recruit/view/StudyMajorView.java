package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.StudyMajorModel;

/**
 * Created by zty on 2017/3/31.
 */

public interface StudyMajorView extends IBaseView {

    void onStudyMajorList(List<StudyMajorModel> majorModels);
}
