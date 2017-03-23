package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.IndustryTypeModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface IndustryTypeView extends IBaseView {
    void onIndustryTypeSuccess(List<IndustryTypeModel> models);
}
