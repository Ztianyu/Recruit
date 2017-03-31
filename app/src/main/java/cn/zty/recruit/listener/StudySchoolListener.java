package cn.zty.recruit.listener;

/**
 * Created by zty on 2017/3/31.
 */

public interface StudySchoolListener {

    void onStudySchoolSure(String provinceId, String schoolType, String studyType, String discipline, String majorId, String tuition);
}
