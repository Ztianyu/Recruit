package cn.zty.recruit.listener;

import cn.zty.recruit.bean.MajorModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface SchoolSelectListener {

    void onSchoolSelect(String provinceId, MajorModel majorModel, String score,String examinationType);
}
