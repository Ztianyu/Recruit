package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.PlanModel;

/**
 * Created by zty on 2017/3/23.
 */

public interface ScheduleSetListView extends IBaseView {
    void onScheduleSetSuccess(List<PlanModel> models);
}
