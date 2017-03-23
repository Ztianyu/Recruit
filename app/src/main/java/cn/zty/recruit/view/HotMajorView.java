package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.MajorModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface HotMajorView extends IBaseView {
    void onHotMajorSuccess(List<MajorModel> majorModels);
}
