package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.VocationalModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface VocationView extends IBaseView {
    void onVocationSuccess(VocationalModel model);
}
