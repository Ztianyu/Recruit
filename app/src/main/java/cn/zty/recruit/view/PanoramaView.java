package cn.zty.recruit.view;

import cn.zty.baselib.view.IBaseView;
import cn.zty.recruit.bean.PanoramaModel;

/**
 * Created by zty on 2017/3/22.
 */

public interface PanoramaView extends IBaseView {
    void onPanoramaSuccess(PanoramaModel model);
}
