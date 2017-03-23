package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.CollegeModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface DepartmentListView extends IBaseView {
    void onDepartmentSuccess(List<CollegeModel> models);
}
