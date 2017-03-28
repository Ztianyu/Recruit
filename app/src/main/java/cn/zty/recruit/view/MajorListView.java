package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.DepartmentMajorModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface MajorListView extends IBaseView {
    void onMajorListSuccess(List<DepartmentMajorModel> majorModels);
}
