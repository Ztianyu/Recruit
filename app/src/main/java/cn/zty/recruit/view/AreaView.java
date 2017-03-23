package cn.zty.recruit.view;

import java.util.List;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.TipModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface AreaView extends IBaseView {
    void onAreaSuccess(int type, List<TipModel> models);
}
