package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.TrainingModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface TrainOrgListView extends IBaseView {
    void onTrainOrgListSuccess(List<TrainingModel> models);
}
